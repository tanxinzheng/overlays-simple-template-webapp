package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.GroupPermission;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
public @Data class GroupPermissionCreate implements Serializable {

    private String id;
    private String groupId;
    private String permissionId;

    public GroupPermission getEntity(){
        GroupPermission groupPermission = new GroupPermission();
        BeanUtils.copyProperties(this, groupPermission);
        return groupPermission;
    }
}