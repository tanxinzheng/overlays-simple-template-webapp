package com.xmomen.module.authorization.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "xmo_user_group")
public class UserGroup extends BaseMybatisModel {
    /**
     * 
     */
    private String id;

    /**
     * 用户表ID
     */
    private String userId;

    /**
     * 组表ID
     */
    private String groupId;

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
}