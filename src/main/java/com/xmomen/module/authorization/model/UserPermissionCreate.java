package com.xmomen.module.authorization.model;

import com.xmomen.module.authorization.entity.UserPermission;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
public @Data class UserPermissionCreate implements Serializable {

    /** 主键 */
    private String id;
    /** 用户表ID */
    private String userId;
    /** 权限表ID */
    private String permissionId;

    public UserPermission getEntity(){
        UserPermission userPermission = new UserPermission();
        BeanUtils.copyProperties(this, userPermission);
        return userPermission;
    }
}
