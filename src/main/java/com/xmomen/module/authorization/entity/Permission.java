package com.xmomen.module.authorization.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "xmo_permission")
public class Permission extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 权限代码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 激活
     */
    private Boolean active;

    /**
     * 创建时间
     */
    private Date createDate;

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

    @Column(name = "PERMISSION_NAME")
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
        if(permissionName == null){
              removeValidField("permissionName");
              return;
        }
        addValidField("permissionName");
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

    @Column(name = "ACTIVE")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
        if(active == null){
              removeValidField("active");
              return;
        }
        addValidField("active");
    }

    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
        if(createDate == null){
              removeValidField("createDate");
              return;
        }
        addValidField("createDate");
    }
}