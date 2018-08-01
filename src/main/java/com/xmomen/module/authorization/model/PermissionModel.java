package com.xmomen.module.authorization.model;

import com.xmomen.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.authorization.constant.PermissionAction;
import com.xmomen.module.authorization.model.Permission;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@ExcelTarget(value = "PermissionModel")
public @Data class PermissionModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 权限代码 */
    @Excel(name = "权限代码")
    @NotBlank(message = "权限代码为必填项")
    @Length(max = 50, message = "权限代码字符长度限制[0,50]")
    private String permissionCode;
    /** 权限名称 */
    @Excel(name = "权限名称")
    @NotBlank(message = "权限名称为必填项")
    @Length(max = 100, message = "权限名称字符长度限制[0,100]")
    private String permissionName;
    /** 权限描述 */
    @Excel(name = "权限描述")
    @NotBlank(message = "权限描述为必填项")
    @Length(max = 200, message = "权限描述字符长度限制[0,200]")
    private String description;
    /** 激活 */
    @Excel(name = "激活", replace = { "是_true", "否_false" })
    @NotNull(message = "激活为必填项")
    private Boolean active;

    public void setPermissionCode(String permissionCode) {
        if(permissionCode != null){
            this.permissionCode = permissionCode.toUpperCase();
        }
        this.permissionCode = permissionCode;
    }

    /**
    * Get Permission Entity Object
    * @return
    */
    @JsonIgnore
    public Permission getEntity(){
        Permission permission = new Permission();
        BeanUtils.copyProperties(this, permission);
        return permission;
    }


}
