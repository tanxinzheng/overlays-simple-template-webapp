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
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@ExcelTarget(value = "NotificationSendModel")
public @Data class NotificationSendModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /**
     * 模板代码
     */
    private String templateCode;
    /**
     * 通知类型
     */
    @NotBlank
    private String notificationType;
    /** 通知内容表主键 */
//    @NotBlank(message = "通知内容表主键为必填项")
    @Length(max = 32, message = "通知内容表主键字符长度限制[0,32]")
    private String notificationId;
    /** 发送人 */
    @Excel(name = "发送人")
//    @NotBlank(message = "发送人为必填项")
    @Length(max = 32, message = "发送人字符长度限制[0,32]")
    private String sender;
    /**
     * 收件人
     */
    private String[] receivers;
    /**
     * 收件用户组
     */
    private String[] receiveGroup;
    /**
     * 失效时间
     */
    private Date expireTime;
    /** 发送时间 */
    @Excel(name = "发送时间")
    @NotNull(message = "发送时间为必填项")
    private Date sendTime;
    /** 状态 */
    @Excel(name = "状态")
//    @NotBlank(message = "状态为必填项")
    @Length(max = 10, message = "状态字符长度限制[0,10]")
    private String dataState;

    /**
    * Get NotificationSend Entity Object
    * @return
    */
    @JsonIgnore
    public NotificationSend getEntity(){
        NotificationSend notificationSend = new NotificationSend();
        BeanUtils.copyProperties(this, notificationSend);
        return notificationSend;
    }


}
