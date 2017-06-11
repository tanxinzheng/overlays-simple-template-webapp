package com.xmomen.module.system.model;

import com.xmomen.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.system.model.Dictionary;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 1:07:45
 * @version 1.0.0
 */
@ExcelTarget(value = "DictionaryModel")
public @Data class DictionaryModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 字典组名称 */
    @Excel(name = "字典组名称")
    @NotBlank(message = "字典组名称为必填项")
    @Length(max = 50, message = "字典组名称字符长度限制[0,50]")
    private String groupName;
    /** 字典组代码 */
    @Excel(name = "字典组代码")
    @NotBlank(message = "字典组代码为必填项")
    @Length(max = 50, message = "字典组代码字符长度限制[0,50]")
    private String groupCode;
    /** 名称 */
    @Excel(name = "名称")
    @NotBlank(message = "名称为必填项")
    @Length(max = 50, message = "名称字符长度限制[0,50]")
    private String dictionaryName;
    /** 代码 */
    @Excel(name = "代码")
    @NotBlank(message = "代码为必填项")
    @Length(max = 50, message = "代码字符长度限制[0,50]")
    private String dictionaryCode;
    /** 排序 */
    @Excel(name = "排序")
    @NotNull(message = "排序为必填项")
    @Range(max = 999999999, min = -999999999, message = "排序数值范围[999999999,-999999999]")
    private Integer sort;
    /** 激活 */
    @Excel(name = "激活")
    @NotNull(message = "激活为必填项")
    private Boolean active;
    /** 父节点 */
    @Excel(name = "父节点")
    @Length(max = 32, message = "父节点字符长度限制[0,32]")
    private String parentId;
    /** 显示 */
    @Excel(name = "显示")
    @NotNull(message = "显示为必填项")
    private Boolean isShow;

    /**
    * Get Dictionary Entity Object
    * @return
    */
    @JsonIgnore
    public Dictionary getEntity(){
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(this, dictionary);
        return dictionary;
    }


}
