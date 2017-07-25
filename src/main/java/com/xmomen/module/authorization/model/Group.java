package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.framework.model.BaseEntity;

import java.lang.Boolean;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
public @Data class Group extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 用户组代码 */
    private String groupCode;
    /** 用户组名称 */
    private String groupName;
    /** 用户组描述 */
    private String description;
    /** 激活 */
    private Boolean active;


}
