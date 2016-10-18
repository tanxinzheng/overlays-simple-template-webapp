package com.xmomen.module.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.user.entity.User;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:09:38
 * @version 1.0.0
 */
@ExcelTarget(value = "UserModel")
public @Data class UserModel implements Serializable {

    /**  */
    private String id;
    /** 用户名 */
    @Excel(name = "用户名")
    private String username;
    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String nickname;
    /** 密码盐值 */
    private String salt;
    /** 密码 */
    private String password;
    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;
    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;
    /** 禁用 */
    @Excel(name = "禁用")
    private Boolean isLock;
    @JsonIgnore
    public User getEntity(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }


}
