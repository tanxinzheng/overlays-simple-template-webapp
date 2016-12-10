package com.xmomen.module.authorization.model;

import com.xmomen.module.authorization.entity.Permission;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2016-11-20 16:35:44
 * @version 1.0.0
 */
public @Data class PermissionCreate implements Serializable {

    /** 主键 */
    private String id;
    /** 权限代码 */
    @Excel(name = "权限代码", isImportField = "true")
    @Length(min = 4, max = 50, message = "权限代码长度范围[4,50]")
    @NotBlank(message = "权限代码不能为空")
    private String permissionCode;
    /** 权限名称 */
    @Excel(name = "权限名称", isImportField = "true")
    @NotNull(message = "权限名称不能为空")
    @NotBlank(message = "权限名称不能为空")
    private String permissionName;
    /** 权限描述 */
    @Excel(name = "权限描述", isImportField = "true")
    private String description;
    /** 激活 */
    @Excel(name = "激活", isImportField = "true", replace = {"是_1","否_0"})
    private Boolean active;
    /** 创建时间 */
    @Excel(name = "创建时间", isImportField = "true", importFormat = "yyyyMMdd")
    private Date createDate;

    public Permission getEntity(){
        Permission permission = new Permission();
        BeanUtils.copyProperties(this, permission);
        return permission;
    }
}
