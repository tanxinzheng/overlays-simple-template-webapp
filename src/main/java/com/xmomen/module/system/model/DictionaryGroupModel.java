package com.xmomen.module.system.model;

import com.xmomen.module.system.entity.DictionaryGroup;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Boolean;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:24:57
 * @version 1.0.0
 */
@ExcelTarget(value = "DictionaryGroupModel")
public @Data class DictionaryGroupModel implements Serializable {

    /**  */
    private String id;
    /** 字典编号 */
    @Excel(name = "字典编号")
    private String dictionaryType;
    /** 字典描述 */
    @Excel(name = "字典描述")
    private String dictionaryDesc;
    /** 激活 */
    @Excel(name = "激活")
    private Boolean active;

    public DictionaryGroup getEntity(){
        DictionaryGroup dictionaryGroup = new DictionaryGroup();
        BeanUtils.copyProperties(this, dictionaryGroup);
        return dictionaryGroup;
    }


}