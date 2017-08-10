package com.xmomen.module.core.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/1/6.
 */
public @Data
class Register implements Serializable {

    /**
     * 注册类型：1-手机注册，2-邮箱注册
     */
    private String type;

    @NotBlank
    private String username;
    @Email
    private String email;

    private String nickname;

    private String phoneNumber;
    @NotBlank
    private String password;
    @NotBlank
    private String code;

}
