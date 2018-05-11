package com.xmomen.framework.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 字典序列化
 * Created by tanxinzheng on 16/10/20.
 */
@Component
@Scope("prototype")
@Slf4j
public class DictionaryJsonSerializer extends JsonSerializer<Object> {

    Map<DictionaryIndex, DictionaryInterpreterService> dictionaryInterpreterServiceMap = Maps.newConcurrentMap();

    @Autowired
    public void register(List<DictionaryInterpreterService> serviceList){
        for (DictionaryInterpreterService dictionaryInterpreterService : serviceList) {
            dictionaryInterpreterServiceMap.put(dictionaryInterpreterService.getDictionaryIndex(), dictionaryInterpreterService);
        }
    }

    /**
     * 字典翻译器
     */
    private DictionaryInterpreter dictionaryInterpreter;

    private String fieldName;

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(value == null){
            return;
        }
        jsonGenerator.writeObject(value);
        try {
            DictionaryInterpreterService dictionaryInterpreterService = dictionaryInterpreterServiceMap.get(dictionaryInterpreter.index());
            if(dictionaryInterpreterService == null){
                return;
            }
            Object dictionaryLabel = dictionaryInterpreterService.translateDictionary(dictionaryInterpreter.index(), String.valueOf(value));
            if(dictionaryInterpreter.index().getJsonType().equals(DictionaryIndex.JsonType.STRING)){
                if(StringUtils.trimToNull(dictionaryInterpreter.fieldName()) != null){
                    jsonGenerator.writeStringField(dictionaryInterpreter.fieldName(), (String)dictionaryLabel);
                }else{
                    String currentName = jsonGenerator.getOutputContext().getCurrentName();
                    jsonGenerator.writeStringField(currentName + "Desc", (String)dictionaryLabel);
                }
            }else if(dictionaryInterpreter.index().getJsonType().equals(DictionaryIndex.JsonType.OBJECT)) {
                if(StringUtils.trimToNull(dictionaryInterpreter.fieldName()) != null){
                    jsonGenerator.writeObjectField(dictionaryInterpreter.fieldName(), dictionaryLabel);
                }else{
                    String currentName = jsonGenerator.getOutputContext().getCurrentName();
                    jsonGenerator.writeObjectField(currentName + "Desc", dictionaryLabel);
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }

    }

    public DictionaryJsonSerializer(DictionaryInterpreter dictionaryInterpreter, String fieldName) {
        this.dictionaryInterpreter = dictionaryInterpreter;
        this.fieldName = fieldName;
    }
}
