package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.UserPermission;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:46:57
 * @version 1.0.0
 */
public @Data class UserPermissionUpdate implements Serializable {

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
