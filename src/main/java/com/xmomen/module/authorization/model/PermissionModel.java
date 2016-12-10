package com.xmomen.module.authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.authorization.entity.Permission;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2016-11-20 16:35:44
 * @version 1.0.0
 */
@ExcelTarget(value = "PermissionModel")
public @Data class PermissionModel implements Serializable {

    /** 主键 */
    private String id;
    /** 权限代码 */
    @Excel(name = "权限代码", width = 50)
    private String permissionCode;
    /** 权限名称 */
    @Excel(name = "权限名称", width = 50)
    private String permissionName;
    /** 权限描述 */
    @Excel(name = "权限描述", width = 100)
    private String description;
    /** 激活 */
    @Excel(name = "激活", replace = {"是_true", "否_false"})
    private Boolean active;
    /** 创建时间 */
    @Excel(name = "创建时间", exportFormat = "yyyyMMdd")
    private Date createDate;
    @JsonIgnore
    public Permission getEntity(){
        Permission permission = new Permission();
        BeanUtils.copyProperties(this, permission);
        return permission;
    }


}
