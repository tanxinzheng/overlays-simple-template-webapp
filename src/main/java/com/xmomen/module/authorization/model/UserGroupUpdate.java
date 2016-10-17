package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.UserGroup;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
public @Data class UserGroupUpdate implements Serializable {

    private String id;
    private String userId;
    private String groupId;


    public UserGroup getEntity(){
        UserGroup userGroup = new UserGroup();
        BeanUtils.copyProperties(this, userGroup);
        return userGroup;
    }
}
