package com.xmomen.module.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import com.xmomen.module.user.entity.User;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-20 1:05:48
 * @version 1.0.0
 */
@ExcelTarget(value = "UserModel")
public @Data class UserModel implements Serializable {

    /** 主键 */
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
    /** 锁定 */
    @Excel(name = "锁定")
    private Boolean locked;
    /** 注册时间 */
    @Excel(name = "注册时间")
    private Date createDate;
    /** 激活 */
    @Excel(name = "激活")
    @DictionaryInterpreter(type = "active", fieldName = "activeLabel")
    private Boolean active;
    @JsonIgnore
    public User getEntity(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }


}
