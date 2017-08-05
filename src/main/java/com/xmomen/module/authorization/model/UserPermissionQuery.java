package com.xmomen.module.authorization.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/8/3.
 */
@Data
public class UserPermissionQuery implements Serializable {

    private String userId;
    private String username;

}
