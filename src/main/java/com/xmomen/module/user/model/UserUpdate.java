package com.xmomen.module.user.model;

import lombok.Data;
import com.xmomen.module.user.entity.User;
import org.springframework.beans.BeanUtils;

    import java.lang.Boolean;
    import java.lang.String;
    import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-22 21:53:46
 * @version 1.0.0
 */
public @Data class UserUpdate implements Serializable {

    /** 主键 */
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
    /** 锁定 */
    private Boolean locked;
    /** 注册时间 */
    private Date createDate;
    /** 最后登录时间 */
    private Date lastLoginTime;


    public User getEntity(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
