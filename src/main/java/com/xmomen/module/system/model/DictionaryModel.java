package com.xmomen.module.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import com.xmomen.module.system.entity.Dictionary;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
@ExcelTarget(value = "DictionaryModel")
public @Data class DictionaryModel implements Serializable {

    /** 主键 */
    private String id;
    /** 字典类型 */
    @Excel(name = "字典类型")
    @DictionaryInterpreter(type = "")
    private String dictionaryType;
    /** 名称 */
    @Excel(name = "名称")
    private String name;
    /** 代码 */
    @Excel(name = "代码")
    private String code;
    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;
    /** 激活 */
    @Excel(name = "激活")
    private Boolean active;
    /** 父节点 */
    @Excel(name = "父节点")
    private String parentId;
    /** 显示 */
    @Excel(name = "显示")
    private Boolean show;
    @JsonIgnore
    public Dictionary getEntity(){
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(this, dictionary);
        return dictionary;
    }


}
