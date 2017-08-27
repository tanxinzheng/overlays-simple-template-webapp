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
public @Data class NotificationReceive extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 关联通知主键 */
    private String notificationId;
    /** 接收时间 */
    private Date receiveTime;
    /** 接收人 */
    private String receiver;
    /** 状态 */
    private String dataState;


}
