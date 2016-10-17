package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.Permission;
import org.springframework.beans.BeanUtils;

    import java.lang.Boolean;
    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
public @Data class PermissionUpdate implements Serializable {

    private String id;
    private String permissionCode;
    private String permissionName;
    private String description;
    private Boolean active;


    public Permission getEntity(){
        Permission permission = new Permission();
        BeanUtils.copyProperties(this, permission);
        return permission;
    }
}
