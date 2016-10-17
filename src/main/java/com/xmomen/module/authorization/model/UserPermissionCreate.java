package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.UserPermission;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
public @Data class UserPermissionCreate implements Serializable {

    private String id;
    private String userId;
    private String permissionId;

    public UserPermission getEntity(){
        UserPermission userPermission = new UserPermission();
        BeanUtils.copyProperties(this, userPermission);
        return userPermission;
    }
}
