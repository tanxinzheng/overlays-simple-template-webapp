package com.xmomen.module.authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.authorization.entity.UserGroup;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
@ExcelTarget(value = "UserGroupModel")
public @Data class UserGroupModel implements Serializable {

    /**  */
    private String id;
    /** 用户表ID */
    @Excel(name = "用户表ID")
    private String userId;
    /** 组表ID */
    @Excel(name = "组表ID")
    private String groupId;
    @JsonIgnore
    public UserGroup getEntity(){
        UserGroup userGroup = new UserGroup();
        BeanUtils.copyProperties(this, userGroup);
        return userGroup;
    }


}
