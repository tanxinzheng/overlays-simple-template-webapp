package com.xmomen.module.notification.model;

import com.xmomen.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.notification.model.NotificationReceive;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@ExcelTarget(value = "NotificationReceiveModel")
public @Data class NotificationReceiveModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 关联通知主键 */
    @Excel(name = "关联通知主键")
    @NotBlank(message = "关联通知主键为必填项")
    @Length(max = 32, message = "关联通知主键字符长度限制[0,32]")
    private String notificationId;
    /** 接收时间 */
    @Excel(name = "接收时间")
    @NotNull(message = "接收时间为必填项")
    private Date receiveTime;
    /** 接收人 */
    @Excel(name = "接收人")
    @NotBlank(message = "接收人为必填项")
    @Length(max = 32, message = "接收人字符长度限制[0,32]")
    private String receiver;
    /** 状态 */
    @Excel(name = "状态")
    @NotBlank(message = "状态为必填项")
    @Length(max = 10, message = "状态字符长度限制[0,10]")
    private String dataState;

    /**
    * Get NotificationReceive Entity Object
    * @return
    */
    @JsonIgnore
    public NotificationReceive getEntity(){
        NotificationReceive notificationReceive = new NotificationReceive();
        BeanUtils.copyProperties(this, notificationReceive);
        return notificationReceive;
    }


}
