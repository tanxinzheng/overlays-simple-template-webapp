package com.xmomen.module.user.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/1/28.
 */
public @Data
class User implements Serializable {
    private Integer id;
    private String username;
    private String realname;
    private String phoneNumber;
    private String sex;
    private Integer age;
    private String qq;
    private String officeTel;
    private Integer locked;
    private String email;
    private String userGroups;//用户组
    private String userGroupIds;
}
