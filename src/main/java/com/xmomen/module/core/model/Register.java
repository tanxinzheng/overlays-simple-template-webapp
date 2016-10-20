package com.xmomen.module.core.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/1/6.
 */
public @Data
class Register implements Serializable {

    @NotBlank
    private String username;
    @Email
    private String email;

    private String nickname;

    private String phoneNumber;
    @NotBlank
    private String password;

}
