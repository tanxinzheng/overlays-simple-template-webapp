package com.xmomen.module.authorization.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "xmo_group_permission")
public class GroupPermission extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 组表ID
     */
    private String groupId;

    /**
     * 权限表ID
     */
    private String permissionId;

    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "GROUP_ID")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
        if(groupId == null){
              removeValidField("groupId");
              return;
        }
        addValidField("groupId");
    }

    @Column(name = "PERMISSION_ID")
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
        if(permissionId == null){
              removeValidField("permissionId");
              return;
        }
        addValidField("permissionId");
    }
}