package com.xmomen.module.authorization.model;

import com.xmomen.module.authorization.entity.GroupPermission;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
@ExcelTarget(value = "GroupPermissionModel")
public @Data class GroupPermissionModel implements Serializable {

    /** 主键 */
    private String id;
    /** 组表ID */
    @Excel(name = "组表ID")
    private String groupId;
    /** 权限表ID */
    @Excel(name = "权限表ID")
    private String permissionId;

    public GroupPermission getEntity(){
        GroupPermission groupPermission = new GroupPermission();
        BeanUtils.copyProperties(this, groupPermission);
        return groupPermission;
    }


}
