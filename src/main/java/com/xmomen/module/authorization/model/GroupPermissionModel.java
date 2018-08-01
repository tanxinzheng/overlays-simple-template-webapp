package com.xmomen.module.authorization.model;

import com.xmomen.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.authorization.model.GroupPermission;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@ExcelTarget(value = "GroupPermissionModel")
public @Data class GroupPermissionModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 组表ID */
    @Excel(name = "组表ID")
    @NotBlank(message = "组表ID为必填项")
    @Length(max = 32, message = "组表ID字符长度限制[0,32]")
    private String groupId;
    /** 权限表ID */
    @Excel(name = "权限表ID")
    @NotBlank(message = "权限表ID为必填项")
    @Length(max = 32, message = "权限表ID字符长度限制[0,32]")
    private String permissionId;

    /**
    * Get GroupPermission Entity Object
    * @return
    */
    @JsonIgnore
    public GroupPermission getEntity(){
        GroupPermission groupPermission = new GroupPermission();
        BeanUtils.copyProperties(this, groupPermission);
        return groupPermission;
    }


}
