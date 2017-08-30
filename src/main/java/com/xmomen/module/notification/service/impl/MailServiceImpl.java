package com.xmomen.module.notification.service.impl;

import com.xmomen.module.notification.model.MailMessageModel;
import com.xmomen.module.notification.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by tanxinzheng on 17/8/27.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 发送邮件
     *
     * @param mailMessageModel
     */
    @Override
    public void sendMail(MailMessageModel mailMessageModel) {
        Assert.notNull(mailMessageModel.getFrom(), "发件人必填");
        Assert.notEmpty(mailMessageModel.getTo(), "收件人必填");
        Assert.notNull(mailMessageModel.getSubject(), "标题必填");
        Assert.notNull(mailMessageModel.getText(), "内容必填");
        javaMailSender.send(mailMessageModel);
    }
}
