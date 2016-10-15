package com.xmomen.module.system.model;

import com.xmomen.module.system.entity.Dictionary;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-15 14:41:37
 * @version 1.0.0
 */
public @Data class DictionaryModel implements Serializable {

    private String id;
    private String dictionaryType;
    private String showValue;
    private String codeValue;
    private Integer sortValue;
    private Boolean active;
    private String parentId;
    private Boolean show;

    public Dictionary getEntity(){
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(this, dictionary);
        return dictionary;
    }


}
