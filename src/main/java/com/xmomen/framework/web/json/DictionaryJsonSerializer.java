package com.xmomen.framework.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

/**
 * 字典序列化
 * Created by tanxinzheng on 16/10/20.
 */
public class DictionaryJsonSerializer extends JsonSerializer<Object> {

    /**
     * 字典翻译器接口
     */
    private DictionaryInterpreterService dictionaryInterpreterService;

    public DictionaryInterpreterService getDictionaryInterpreterService() {
        return dictionaryInterpreterService;
    }

    public void setDictionaryInterpreterService(DictionaryInterpreterService dictionaryInterpreterService) {
        this.dictionaryInterpreterService = dictionaryInterpreterService;
    }

    /**
     * 字典翻译器
     */
    private DictionaryInterpreter dictionaryInterpreter;

    public DictionaryInterpreter getDictionaryInterpreter() {
        return dictionaryInterpreter;
    }

    public void setDictionaryInterpreter(DictionaryInterpreter dictionaryInterpreter) {
        this.dictionaryInterpreter = dictionaryInterpreter;
    }

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(value == null){
            return;
        }
        jsonGenerator.writeObject(value);
        String dictionaryLabel = dictionaryInterpreterService.translate(dictionaryInterpreter.type(), String.valueOf(value));
        if(StringUtils.trimToNull(dictionaryInterpreter.fieldName()) != null){
            jsonGenerator.writeStringField(dictionaryInterpreter.fieldName(), dictionaryLabel);
        }
    }
}
