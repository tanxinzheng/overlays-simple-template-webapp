package com.xmomen.module.authorization.model;

import com.xmomen.module.authorization.entity.UserGroup;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
public @Data class UserGroupCreate implements Serializable {

    /**  */
    private String id;
    /** 用户表ID */
    private String userId;
    /** 组表ID */
    private String groupId;

    public UserGroup getEntity(){
        UserGroup userGroup = new UserGroup();
        BeanUtils.copyProperties(this, userGroup);
        return userGroup;
    }
}
