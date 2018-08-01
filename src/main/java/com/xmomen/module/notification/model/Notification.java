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
public @Data class Notification extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 模板主键 */
    private String templateId;
    /** 标题 */
    private String title;
    /** 内容 */
    private String body;
    /** 失效时间 */
    private Date expireTime;
    /** 通知类型 */
    private String notificationType;


}
