package com.xmomen.module.user.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public @Data
class CreateUser implements Serializable {

    @NotNull
    @NotBlank
    private String username;
    @NotNull
    private String email;
    @NotNull
    @NotBlank
    private String password;
    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 年龄
     */
    private Integer age;
    /**
     * 1-男，2女
     */
    private Integer sex;

    /**
     * QQ
     */
    private String qq;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 办公室电话
     */
    private String officeTel;

    private Boolean locked = Boolean.FALSE;

    @NotNull
    @NotEmpty
    private Integer[] userGroupIds;

    public CreateUser() {
    }

    public CreateUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
