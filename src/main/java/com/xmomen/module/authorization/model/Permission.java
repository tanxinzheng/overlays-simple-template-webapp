package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.framework.model.BaseEntity;

import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public @Data class Permission extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 权限代码 */
    private String permissionCode;
    /** 权限名称 */
    private String permissionName;
    /** 权限描述 */
    private String description;
    /** 激活 */
    private Boolean active;
    /** 创建人 */
    private String createdUserId;
    /** 创建时间 */
    private Date createdTime;
    /** 更新人 */
    private String updatedUserId;
    /** 更新时间 */
    private Date updatedTime;
    /** 数据版本号 */
    private Integer dataVersion;

    public void setPermissionCode(String permissionCode) {
        if(permissionCode != null){
            this.permissionCode = permissionCode.toUpperCase();
        }
        this.permissionCode = permissionCode;
    }
}
