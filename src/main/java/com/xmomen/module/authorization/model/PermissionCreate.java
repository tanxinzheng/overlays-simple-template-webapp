package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.Permission;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.lang.Boolean;
    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:46:57
 * @version 1.0.0
 */
public @Data class PermissionCreate implements Serializable {

    /** 主键 */
    private String id;
    /** 权限代码 */
    @NotNull(message = "权限代码必填")
    @NotEmpty(message = "权限代码必填")
    @Length(min = 3, max = 10, message = "{com.xmomen.module.authorization.model.PermissionCreate.permissionCode.Length.message}")
    private String permissionCode;
    /** 权限名称 */
    @NotNull(message = "权限代码必填")
    @NotEmpty(message = "权限名称必填")
    @Length(max = 6, message = "权限名称长度限制为[0,{max}]")
    private String permissionName;
    /** 权限描述 */
    private String description;
    /** 激活 */
    private Boolean active;

    public Permission getEntity(){
        Permission permission = new Permission();
        BeanUtils.copyProperties(this, permission);
        return permission;
    }
}
