package com.xmomen.module.mail.service;

import com.xmomen.module.mail.model.EmailModel;

/**
 * Created by tanxinzheng on 18/5/13.
 */
public interface EmailService {

    /**
     * 发送邮件
     * @param emailModel
     */
    void sendEmail(EmailModel emailModel);

}
