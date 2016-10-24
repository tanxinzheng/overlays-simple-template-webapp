package com.xmomen.module.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.notification.entity.SystemNotification;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
@ExcelTarget(value = "SystemNotificationModel")
public @Data class SystemNotificationModel implements Serializable {

    /** 主键 */
    private String id;
    /** 发送者主键 */
    @Excel(name = "发送者主键")
    private String senderId;
    /** 接收者主键 */
    @Excel(name = "接收者主键")
    private String receiverId;
    /** 内容主键 */
    @Excel(name = "内容主键")
    private String textId;
    /** 已读 */
    @Excel(name = "已读")
    private Boolean readed;
    /** 状态 */
    @Excel(name = "状态")
    private String status;
    /** 过期时间 */
    @Excel(name = "过期时间")
    private Date expireTime;
    @JsonIgnore
    public SystemNotification getEntity(){
        SystemNotification systemNotification = new SystemNotification();
        BeanUtils.copyProperties(this, systemNotification);
        return systemNotification;
    }


}