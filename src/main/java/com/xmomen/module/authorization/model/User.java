package com.xmomen.module.authorization.model;

import com.xmomen.framework.web.json.DictionaryInterpreter;
import lombok.Data;
import com.xmomen.framework.model.BaseEntity;

import java.lang.Boolean;
import java.lang.String;
import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-6-16 22:59:53
 * @version 1.0.0
 */
public @Data class User extends BaseEntity implements Serializable {

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
    /** 头像 */
    @DictionaryInterpreter(type = "ATTACHMENT_KEY", fieldName = "avatarUrl")
    private String avatar;
    /** 锁定 */
    private Boolean locked;
    /** 注册时间 */
    private Date createDate;
    /** 最后登录时间 */
    private Date lastLoginTime;


}
