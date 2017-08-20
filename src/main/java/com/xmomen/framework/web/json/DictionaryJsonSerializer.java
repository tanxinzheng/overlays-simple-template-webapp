package com.xmomen.framework.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 字典序列化
 * Created by tanxinzheng on 16/10/20.
 */
@Component
@Scope("prototype")
public class DictionaryJsonSerializer extends JsonSerializer<Object> {

    private static Logger logger = LoggerFactory.getLogger(DictionaryJsonSerializer.class);

    @Autowired
    DictionaryInterpreterService dictionaryInterpreterService;

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
            String dictionaryLabel = dictionaryInterpreterService.translate(dictionaryInterpreter.index(), String.valueOf(value));
            if(StringUtils.trimToNull(dictionaryInterpreter.fieldName()) != null){
                jsonGenerator.writeStringField(dictionaryInterpreter.fieldName(), dictionaryLabel);
            }else{
                String currentName = jsonGenerator.getOutputContext().getCurrentName();
                jsonGenerator.writeStringField(currentName + "Desc", dictionaryLabel);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }

    }

    public DictionaryJsonSerializer(DictionaryInterpreter dictionaryInterpreter, String fieldName) {
        this.dictionaryInterpreter = dictionaryInterpreter;
        this.fieldName = fieldName;
    }
}
