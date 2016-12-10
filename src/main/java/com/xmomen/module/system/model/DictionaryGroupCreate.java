package com.xmomen.module.system.model;

import com.xmomen.module.system.entity.DictionaryGroup;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-12-7 1:47:07
 * @version 1.0.0
 */
public @Data class DictionaryGroupCreate implements Serializable {

    /**  */
    private String id;
    /** 字典编号 */
    private String dictionaryType;
    /** 字典描述 */
    private String dictionaryDesc;
    /** 激活 */
    private boolean active;

    public DictionaryGroup getEntity(){
        DictionaryGroup dictionaryGroup = new DictionaryGroup();
        BeanUtils.copyProperties(this, dictionaryGroup);
        return dictionaryGroup;
    }
}
