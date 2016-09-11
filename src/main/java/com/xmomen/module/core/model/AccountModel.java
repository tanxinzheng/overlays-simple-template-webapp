package com.xmomen.module.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 16/9/2.
 */
public @Data class AccountModel implements Serializable {

    private String username;
    private String password;
    private String salt;
    private Boolean locked;
}
