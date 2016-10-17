package com.xmomen.module.system.model;

import com.xmomen.module.system.entity.Dictionary;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
@ExcelTarget(value = "DictionaryModel")
public @Data class DictionaryModel implements Serializable {

    /** 主键 */
    private String id;
    /** 字典类型 */
    @Excel(name = "字典类型")
    private String dictionaryType;
    /** 显示值 */
    @Excel(name = "显示值")
    private String showValue;
    /** 实际值 */
    @Excel(name = "实际值")
    private String codeValue;
    /** 排序 */
    @Excel(name = "排序")
    private Integer sortValue;
    /** 激活 */
    @Excel(name = "激活")
    private Boolean active;
    /** 父节点 */
    @Excel(name = "父节点")
    private String parentId;
    /** 显示 */
    @Excel(name = "显示")
    private Boolean show;

    public Dictionary getEntity(){
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(this, dictionary);
        return dictionary;
    }


}
