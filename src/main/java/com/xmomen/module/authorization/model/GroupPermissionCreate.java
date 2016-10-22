package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.GroupPermission;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-20 23:14:13
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
