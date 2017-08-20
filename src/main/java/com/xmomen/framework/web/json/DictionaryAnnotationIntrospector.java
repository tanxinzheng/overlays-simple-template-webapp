package com.xmomen.framework.web.json;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 字典注解
 */
public class DictionaryAnnotationIntrospector extends JacksonAnnotationIntrospector implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object findSerializer(Annotated a) {
        DictionaryInterpreter dictionaryInterpreter = a.getAnnotation(DictionaryInterpreter.class);
        if(dictionaryInterpreter != null) {
            DictionaryJsonSerializer dictionaryJsonSerializer = applicationContext.getBean(DictionaryJsonSerializer.class, dictionaryInterpreter, dictionaryInterpreter.fieldName());
            return dictionaryJsonSerializer;
        }else {
            return super.findSerializer(a);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
