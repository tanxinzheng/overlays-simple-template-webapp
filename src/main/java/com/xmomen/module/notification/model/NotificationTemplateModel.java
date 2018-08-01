package com.xmomen.module.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.framework.model.BaseModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@ExcelTarget(value = "NotificationTemplateModel")
public @Data class NotificationTemplateModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 模板名称 */
    @Excel(name = "模板名称")
    @NotBlank(message = "模板名称为必填项")
    @Length(max = 100, message = "模板名称字符长度限制[0,100]")
    private String templateName;
    /** 模板标题 */
    @Excel(name = "模板标题")
    @NotBlank(message = "模板标题为必填项")
    @Length(max = 200, message = "模板标题字符长度限制[0,200]")
    private String templateTitle;
    /** 模板内容 */
    @Excel(name = "模板内容")
    @NotBlank(message = "模板内容为必填项")
    private String templateBody;
    /** 模板代码 */
    @Excel(name = "模板代码")
    @NotBlank(message = "模板代码为必填项")
    @Length(max = 100, message = "模板代码字符长度限制[0,100]")
    private String templateCode;
    /** 是否启用 */
    @Excel(name = "是否启用")
    @NotNull(message = "是否启用为必填项")
    private Boolean active;

    /**
    * Get NotificationTemplate Entity Object
    * @return
    */
    @JsonIgnore
    public NotificationTemplate getEntity(){
        NotificationTemplate notificationTemplate = new NotificationTemplate();
        BeanUtils.copyProperties(this, notificationTemplate);
        return notificationTemplate;
    }


}
