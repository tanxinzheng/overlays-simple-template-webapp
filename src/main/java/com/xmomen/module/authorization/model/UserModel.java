package com.xmomen.module.authorization.model;

import com.xmomen.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-6-16 22:59:54
 * @version 1.0.0
 */
@ExcelTarget(value = "UserModel")
public @Data class UserModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 用户名 */
    @Excel(name = "用户名")
    @NotBlank(message = "用户名为必填项")
    @Length(max = 30, message = "用户名字符长度限制[0,30]")
    private String username;
    /** 真实姓名 */
    @Excel(name = "真实姓名")
    @Length(max = 50, message = "真实姓名字符长度限制[0,50]")
    private String nickname;
    /** 密码盐值 */
    @JsonIgnore
    @Excel(name = "密码盐值")
//    @NotBlank(message = "密码盐值为必填项")
    @Length(max = 50, message = "密码盐值字符长度限制[0,50]")
    private String salt;
    /** 密码 */
    @JsonIgnore
    @Excel(name = "密码")
    @NotBlank(message = "密码为必填项")
    @Length(max = 50, message = "密码字符长度限制[0,50]")
    private String password;
    /** 邮箱 */
    @Excel(name = "邮箱")
    @Length(max = 30, message = "邮箱字符长度限制[0,30]")
    private String email;
    /** 手机号码 */
    @Excel(name = "手机号码")
    @Length(max = 20, message = "手机号码字符长度限制[0,20]")
    private String phoneNumber;
    /** 头像 */
    @DictionaryInterpreter(type = "ATTACHMENT_KEY", fieldName = "avatarUrl")
    private String avatar;
    /** 锁定 */
    @Excel(name = "锁定")
    private Boolean locked;
    /** 注册时间 */
    @Excel(name = "注册时间")
    private Date createdTime;
    /** 最后登录时间 */
    @Excel(name = "最后登录时间")
    private Date lastLoginTime;

    /**
    * Get User Entity Object
    * @return
    */
    @JsonIgnore
    public User getEntity(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }


}
