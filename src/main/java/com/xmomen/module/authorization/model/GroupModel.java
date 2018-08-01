package com.xmomen.module.authorization.model;

import com.xmomen.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import com.xmomen.module.authorization.model.Group;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
@ExcelTarget(value = "GroupModel")
public @Data class GroupModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 用户组类型 */
    @Excel(name = "用户组类型")
    @NotBlank(message = "用户组类型为必填项")
    @Length(max = 100, message = "用户组类型字符长度限制[1,100]")
    @DictionaryInterpreter(index = DictionaryIndex.USER_GROUP)
    private String groupType;
    /** 用户组代码 */
    @Excel(name = "用户组代码")
    @NotBlank(message = "用户组代码为必填项")
    @Length(max = 30, message = "用户组代码字符长度限制[1,30]")
    private String groupCode;
    /** 用户组名称 */
    @Excel(name = "用户组名称")
    @NotBlank(message = "用户组名称为必填项")
    @Length(max = 100, message = "用户组名称字符长度限制[1,100]")
    private String groupName;
    /** 用户组描述 */
    @Excel(name = "用户组描述")
    @Length(max = 200, message = "用户组描述字符长度限制[0,200]")
    private String description;
    /** 激活 */
    @Excel(name = "激活")
    private Boolean active;

    /**
    * Get Group Entity Object
    * @return
    */
    @JsonIgnore
    public Group getEntity(){
        Group group = new Group();
        BeanUtils.copyProperties(this, group);
        return group;
    }


}
