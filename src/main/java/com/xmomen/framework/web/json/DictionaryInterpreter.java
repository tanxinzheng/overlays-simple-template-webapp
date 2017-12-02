package com.xmomen.framework.web.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tanxinzheng on 16/10/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DictionaryInterpreter {

    /**
     * 字典类型
     * @return
     */
    DictionaryIndex index();

    /**
     * 字段名称
     * @return
     */
    String fieldName() default "";
}
