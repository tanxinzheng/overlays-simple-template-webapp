package com.xmomen.module.authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.authorization.entity.Group;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-20 23:14:13
 * @version 1.0.0
 */
@ExcelTarget(value = "GroupModel")
public @Data class GroupModel implements Serializable {

    /** 主键 */
    private String id;
    /** 用户组代码 */
    @Excel(name = "用户组代码")
    private String groupCode;
    /** 用户组名称 */
    @Excel(name = "用户组名称")
    private String groupName;
    /** 用户组描述 */
    @Excel(name = "用户组描述")
    private String description;
    /** 激活 */
    @Excel(name = "激活")
    private Boolean active;
    @JsonIgnore
    public Group getEntity(){
        Group group = new Group();
        BeanUtils.copyProperties(this, group);
        return group;
    }


}
