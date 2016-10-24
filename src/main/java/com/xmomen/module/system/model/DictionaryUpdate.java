package com.xmomen.module.system.model;

import com.xmomen.module.system.entity.Dictionary;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
public @Data class DictionaryUpdate implements Serializable {

    /** 主键 */
    private String id;
    /** 字典类型 */
    private String dictionaryType;
    /** 名称 */
    private String name;
    /** 代码 */
    private String code;
    /** 排序 */
    private Integer sort;
    /** 激活 */
    private Boolean active;
    /** 父节点 */
    private String parentId;
    /** 显示 */
    private Boolean show;


    public Dictionary getEntity(){
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(this, dictionary);
        return dictionary;
    }
}
