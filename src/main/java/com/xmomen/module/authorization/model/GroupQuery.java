package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.framework.model.BaseQuery;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
public @Data class GroupQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String id;
    private String[] ids;
    private String[] excludeIds;
    private String userId;
    private Boolean active;
    // true：已绑定用户组，false：未绑定用户组
    private Boolean hasBindGroup;

}
