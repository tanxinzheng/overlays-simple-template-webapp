package com.xmomen.module.notification.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tanxinzheng on 17/8/25.
 */
@Data
public class SendNotification implements Serializable {

    /**
     * 模板代码
     */
    private String templateCode;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 接收人
     */
    private String[] receiver;
    /**
     * 接收用户组
     */
    private String[] receiveGroup;
    /**
     * 失效时间
     */
    private Date expireTime;
    
    private String title;
    
    private String body;
}
