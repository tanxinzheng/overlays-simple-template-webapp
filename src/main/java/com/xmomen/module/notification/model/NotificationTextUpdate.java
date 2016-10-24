package com.xmomen.module.notification.model;

import com.xmomen.module.notification.entity.NotificationText;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
public @Data class NotificationTextUpdate implements Serializable {

    /** 主键 */
    private String id;
    /** 提交时间 */
    private Date createTime;
    /** 标题 */
    private String titel;
    /** 消息类型 */
    private String type;
    /** 内容 */
    private String body;


    public NotificationText getEntity(){
        NotificationText notificationText = new NotificationText();
        BeanUtils.copyProperties(this, notificationText);
        return notificationText;
    }
}
