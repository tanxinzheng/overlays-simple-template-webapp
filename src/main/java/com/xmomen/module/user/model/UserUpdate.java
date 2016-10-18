package com.xmomen.module.user.model;

import lombok.Data;
import com.xmomen.module.user.entity.User;
import org.springframework.beans.BeanUtils;

    import java.lang.Boolean;
    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:09:38
 * @version 1.0.0
 */
public @Data class UserUpdate implements Serializable {

    /**  */
    private String id;
    /** 用户名 */
    private String username;
    /** 真实姓名 */
    private String nickname;
    /** 密码盐值 */
    private String salt;
    /** 密码 */
    private String password;
    /** 邮箱 */
    private String email;
    /** 手机号码 */
    private String phoneNumber;
    /** 禁用 */
    private Boolean isLock;


    public User getEntity(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
