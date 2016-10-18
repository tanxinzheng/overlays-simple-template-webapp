package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.UserGroup;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:09:38
 * @version 1.0.0
 */
public @Data class UserGroupCreate implements Serializable {

    /**  */
    private String id;
    /** 用户表ID */
    private String userId;
    /** 组表ID */
    private String groupId;

    public UserGroup getEntity(){
        UserGroup userGroup = new UserGroup();
        BeanUtils.copyProperties(this, userGroup);
        return userGroup;
    }
}
