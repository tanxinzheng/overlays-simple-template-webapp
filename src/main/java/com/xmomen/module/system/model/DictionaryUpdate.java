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
 * @date    2016-10-18 23:46:56
 * @version 1.0.0
 */
public @Data class DictionaryUpdate implements Serializable {

    /** 主键 */
    private String id;
    /** 字典类型 */
    private String dictionaryType;
    /** 显示值 */
    private String showValue;
    /** 实际值 */
    private String codeValue;
    /** 排序 */
    private Integer sortValue;
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
