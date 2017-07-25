package com.xmomen.module.authorization.model;

import com.xmomen.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Excel(name = "激活")
    @NotNull(message = "激活为必填项")
    private Boolean active;
    /** 创建人 */
    @Excel(name = "创建人")
    @NotBlank(message = "创建人为必填项")
    @Length(max = 32, message = "创建人字符长度限制[0,32]")
    private String createdUserId;
    /** 创建时间 */
    @Excel(name = "创建时间")
    @NotNull(message = "创建时间为必填项")
    private Date createdTime;
    /** 更新人 */
    @Excel(name = "更新人")
    @NotBlank(message = "更新人为必填项")
    @Length(max = 32, message = "更新人字符长度限制[0,32]")
    private String updatedUserId;
    /** 更新时间 */
    @Excel(name = "更新时间")
    @NotNull(message = "更新时间为必填项")
    private Date updatedTime;
    /** 数据版本号 */
    @Excel(name = "数据版本号")
    @NotNull(message = "数据版本号为必填项")
    @Range(max = 999999999, min = -999999999, message = "数据版本号数值范围[999999999,-999999999]")
    private Integer dataVersion;

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
