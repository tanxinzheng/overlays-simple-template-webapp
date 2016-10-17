package com.xmomen.module.authorization.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "xmo_group")
public class Group extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户组代码
     */
    private String groupCode;

    /**
     * 用户组名称
     */
    private String groupName;

    /**
     * 用户组描述
     */
    private String description;

    /**
     * 激活
     */
    private Boolean active;

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

    @Column(name = "GROUP_CODE")
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
        if(groupCode == null){
              removeValidField("groupCode");
              return;
        }
        addValidField("groupCode");
    }

    @Column(name = "GROUP_NAME")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
        if(groupName == null){
              removeValidField("groupName");
              return;
        }
        addValidField("groupName");
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
}