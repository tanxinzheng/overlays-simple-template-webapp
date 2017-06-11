package com.xmomen.module.system.model;

import lombok.Data;
import com.xmomen.framework.model.BaseEntity;

import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 1:07:45
 * @version 1.0.0
 */
public @Data class Dictionary extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 字典组名称 */
    private String groupName;
    /** 字典组代码 */
    private String groupCode;
    /** 名称 */
    private String dictionaryName;
    /** 代码 */
    private String dictionaryCode;
    /** 排序 */
    private Integer sort;
    /** 激活 */
    private Boolean active;
    /** 父节点 */
    private String parentId;
    /** 显示 */
    private Boolean isShow;

}
