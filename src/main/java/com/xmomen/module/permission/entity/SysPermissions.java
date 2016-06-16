package com.xmomen.module.permission.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

import javax.persistence.*;

@Entity
@Table(name = "sys_permissions")
public class SysPermissions extends BaseMybatisModel {
    /**
     * 物理主键
     */
    private Integer id;

    /**
     * 权限
     */
    private String permissionCode;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 是否生效：0-禁用，1-启用
     */
    private Integer available;

    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "PERMISSION_CODE")
    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
        if(permissionCode == null){
              removeValidField("permissionCode");
              return;
        }
        addValidField("permissionCode");
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        if(description == null){
              removeValidField("description");
              return;
        }
        addValidField("description");
    }

    @Column(name = "AVAILABLE")
    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
        if(available == null){
              removeValidField("available");
              return;
        }
        addValidField("available");
    }
}