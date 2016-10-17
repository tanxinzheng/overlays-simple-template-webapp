package com.xmomen.module.authorization.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "xmo_user_permission")
public class UserPermission extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户表ID
     */
    private String userId;

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

    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if(userId == null){
              removeValidField("userId");
              return;
        }
        addValidField("userId");
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