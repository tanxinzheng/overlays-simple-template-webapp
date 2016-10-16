package com.xmomen.module.user.model;

import lombok.Data;
import com.xmomen.module.user.entity.User;
import org.springframework.beans.BeanUtils;

    import java.lang.Boolean;
    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-16 20:34:14
 * @version 1.0.0
 */
public @Data class UserUpdate implements Serializable {

    private String id;
    private String username;
    private String nickname;
    private String salt;
    private String password;
    private String email;
    private String phoneNumber;
    private Boolean isLock;


    public User getEntity(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
