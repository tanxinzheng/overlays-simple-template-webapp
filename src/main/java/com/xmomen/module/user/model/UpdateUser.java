package com.xmomen.module.user.model;

import lombok.Data;
import com.xmomen.module.user.entity.User;
import org.springframework.beans.BeanUtils;

    import java.lang.Integer;
    import java.lang.String;
import java.io.Serializable;

/**
 * Created by tanxinzheng on 2016-9-11 18:43:01.
 */
public @Data class UpdateUser implements Serializable {

    private String id;
    private String username;
    private String realname;
    private String salt;
    private String password;
    private String digestKey;
    private Integer sex;
    private String email;
    private String qq;
    private String phoneNumber;
    private String officeTel;
    private Integer locked;


    public User getEntity(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
