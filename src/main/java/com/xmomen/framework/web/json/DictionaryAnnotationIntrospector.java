package com.xmomen.framework.web.json;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 字典注解
 */
public class DictionaryAnnotationIntrospector extends JacksonAnnotationIntrospector implements InitializingBean {

    private DictionaryJsonSerializer dictionaryJsonSerializer;

    private DictionaryInterpreterService dictionaryInterpreterService;

    public DictionaryInterpreterService getDictionaryInterpreterService() {
        return dictionaryInterpreterService;
    }

    public void setDictionaryInterpreterService(DictionaryInterpreterService dictionaryInterpreterService) {
        this.dictionaryInterpreterService = dictionaryInterpreterService;
    }

    public DictionaryJsonSerializer getDictionaryJsonSerializer() {
        return dictionaryJsonSerializer;
    }

    public void setDictionaryJsonSerializer(DictionaryJsonSerializer dictionaryJsonSerializer) {
        this.dictionaryJsonSerializer = dictionaryJsonSerializer;
    }

    public DictionaryAnnotationIntrospector() {
        dictionaryJsonSerializer = new DictionaryJsonSerializer();
        dictionaryJsonSerializer.setDictionaryInterpreterService(dictionaryInterpreterService);
    }

    @Override
    public Object findSerializer(Annotated a) {
        DictionaryInterpreter dictionaryInterpreter = a.getAnnotation(DictionaryInterpreter.class);
        if(dictionaryInterpreter != null) {
            dictionaryJsonSerializer.setDictionaryInterpreter(dictionaryInterpreter);
            if(dictionaryJsonSerializer.getDictionaryInterpreterService() == null){
                dictionaryJsonSerializer.setDictionaryInterpreterService(dictionaryInterpreterService);
            }
            return dictionaryJsonSerializer;
        }else {
            return super.findSerializer(a);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(dictionaryInterpreterService, "Property \\'dictionaryInterpreterService\\' is required");
    }
}
