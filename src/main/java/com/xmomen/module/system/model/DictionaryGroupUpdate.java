package com.xmomen.module.system.model;

import lombok.Data;
import com.xmomen.module.system.entity.DictionaryGroup;
import org.springframework.beans.BeanUtils;

    import java.lang.Boolean;
    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-16 20:34:14
 * @version 1.0.0
 */
public @Data class DictionaryGroupUpdate implements Serializable {

    private String id;
    private String dictionaryType;
    private String dictionaryDesc;
    private Boolean active;


    public DictionaryGroup getEntity(){
        DictionaryGroup dictionaryGroup = new DictionaryGroup();
        BeanUtils.copyProperties(this, dictionaryGroup);
        return dictionaryGroup;
    }
}
