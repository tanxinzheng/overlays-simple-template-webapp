package com.xmomen.module.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.framework.model.BaseModel;
import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@ExcelTarget(value = "NotificationModel")
public @Data class NotificationModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 模板主键 */
    @Excel(name = "模板主键")
    @NotBlank(message = "模板主键为必填项")
    @Length(max = 32, message = "模板主键字符长度限制[0,32]")
    private String templateId;

    @DictionaryInterpreter(index = DictionaryIndex.USER_ID, fieldName = "senderName")
    private String sender;
    private String sendTime;
    @DictionaryInterpreter(index = DictionaryIndex.NOTIFICATION_DATA_STATE, fieldName = "dataStateName")
    private String dataState;
    /** 标题 */
    @Excel(name = "标题")
    @NotBlank(message = "标题为必填项")
    @Length(max = 500, message = "标题字符长度限制[0,500]")
    private String title;
    /** 内容 */
    @Excel(name = "内容")
    @NotBlank(message = "内容为必填项")
    private String body;
    /** 失效时间 */
    @Excel(name = "失效时间")
    private Date expireTime;
    /** 通知类型 */
    @Excel(name = "通知类型")
    @NotBlank(message = "通知类型为必填项")
    @Length(max = 20, message = "通知类型字符长度限制[0,20]")
    private String notificationType;

    /**
    * Get Notification Entity Object
    * @return
    */
    @JsonIgnore
    public Notification getEntity(){
        Notification notification = new Notification();
        BeanUtils.copyProperties(this, notification);
        return notification;
    }


}
