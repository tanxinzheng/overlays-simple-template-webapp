package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.framework.model.BaseEntity;

import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public @Data class UserGroup extends BaseEntity implements Serializable {

    /**  */
    private String id;
    /** 用户表ID */
    private String userId;
    /** 组表ID */
    private String groupId;


}
