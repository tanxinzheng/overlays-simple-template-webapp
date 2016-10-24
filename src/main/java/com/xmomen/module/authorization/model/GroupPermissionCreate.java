package com.xmomen.module.authorization.model;

import com.xmomen.module.authorization.entity.GroupPermission;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
public @Data class GroupPermissionCreate implements Serializable {

    /** 主键 */
    private String id;
    /** 组表ID */
    private String groupId;
    /** 权限表ID */
    private String permissionId;

    public GroupPermission getEntity(){
        GroupPermission groupPermission = new GroupPermission();
        BeanUtils.copyProperties(this, groupPermission);
        return groupPermission;
    }
}
