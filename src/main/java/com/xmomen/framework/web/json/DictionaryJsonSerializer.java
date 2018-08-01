package com.xmomen.framework.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
                dictionaryInterpreterService = dictionaryInterpreterServiceMap.get(DictionaryIndex.DICTIONARY);
            }
            if(dictionaryInterpreterService == null){
                return;
            }
            Map<String, Object> dictionaryMapLabel = dictionaryInterpreterService.translateDictionary(dictionaryInterpreter.index(), (String) value);
            String currentName = jsonGenerator.getOutputContext().getCurrentName();
            if(dictionaryInterpreter.outFormat().equals(TransferFormatType.String)){
                String dictionaryLabel = null;
                if(MapUtils.isNotEmpty(dictionaryMapLabel)){
                    dictionaryLabel = (String) dictionaryMapLabel.get(value);
                }
                if(StringUtils.trimToNull(dictionaryInterpreter.fieldName()) != null){
                    jsonGenerator.writeStringField(dictionaryInterpreter.fieldName(), dictionaryLabel);
                }else{
                    jsonGenerator.writeStringField(currentName + "Desc", dictionaryLabel);
                }
            }else if(dictionaryInterpreter.outFormat().equals(TransferFormatType.Object)) {
                Object dictionaryLabel = null;
                if(MapUtils.isNotEmpty(dictionaryMapLabel)){
                    dictionaryLabel = dictionaryMapLabel.get(value);
                }
                if(StringUtils.trimToNull(dictionaryInterpreter.fieldName()) != null){
                    jsonGenerator.writeObjectField(dictionaryInterpreter.fieldName(), dictionaryLabel);
                }else{
                    if(DictionaryIndex.ACCOUNT.equals(dictionaryInterpreter.index())){
                        jsonGenerator.writeObjectField("account", dictionaryLabel);
                    }else{
                        jsonGenerator.writeObjectField(currentName + "Desc", dictionaryLabel);
                    }
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
