package com.xmomen.module.notification.model;

import lombok.Data;
import com.xmomen.framework.model.BaseEntity;

import java.util.Date;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public @Data class NotificationSend extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 通知内容表主键 */
    private String notificationId;
    /** 发送人 */
    private String sender;
    /** 发送时间 */
    private Date sendTime;
    /** 状态 */
    private String dataState;


}
