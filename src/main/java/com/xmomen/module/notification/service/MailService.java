package com.xmomen.module.notification.service;

import com.xmomen.module.notification.model.MailMessageModel;

/**
 * Created by tanxinzheng on 17/8/27.
 */
public interface MailService {

    /**
     * 发送邮件
     * @param mailMessageModel
     */
    void sendMail(MailMessageModel mailMessageModel);
}
