package com.xmomen.module.system.model;

import lombok.Data;
import com.xmomen.module.system.entity.Dictionary;
import org.springframework.beans.BeanUtils;

    import java.lang.Boolean;
    import java.lang.String;
    import java.lang.Integer;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:24:57
 * @version 1.0.0
 */
public @Data class DictionaryCreate implements Serializable {

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
