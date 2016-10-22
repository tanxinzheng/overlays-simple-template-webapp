package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.Permission;
import org.springframework.beans.BeanUtils;

    import java.math.BigDecimal;
    import java.lang.Boolean;
    import java.lang.String;
    import java.lang.Integer;
    import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-20 23:14:13
 * @version 1.0.0
 */
public @Data class PermissionUpdate implements Serializable {

    /** 主键 */
    private String id;
    /** 权限代码 */
    private String permissionCode;
    /** 权限名称 */
    private String permissionName;
    /** 权限描述 */
    private String description;
    /** 激活 */
    private Boolean active;
    /** 创建时间 */
    private Date createDate;
    /** 金额 */
    private BigDecimal amount;
    /** 年龄 */
    private Integer age;


    public Permission getEntity(){
        Permission permission = new Permission();
        BeanUtils.copyProperties(this, permission);
        return permission;
    }
}
