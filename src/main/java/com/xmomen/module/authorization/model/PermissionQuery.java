package com.xmomen.module.authorization.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-11-20 16:35:44
 * @version 1.0.0
 */
public @Data class PermissionQuery implements Serializable {
    /** 关键字 */
    private String keyword;
    /** 主键 */
    private String id;
    /** 包含主键集 */
    private String[] ids;
    /** 排除主键集 */
    private String[] excludeIds;

    private String groupId;

    private String userId;

    private Boolean hasPermission;

}
