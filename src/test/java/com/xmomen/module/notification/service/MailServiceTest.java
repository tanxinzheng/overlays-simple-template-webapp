package com.xmomen.module.notification.service;

import com.xmomen.module.test.BaseTestController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by tanxinzheng on 17/8/28.
 */
public class MailServiceTest extends BaseTestController {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendMail() throws Exception {
        MailSender mailSender = new JavaMailSenderImpl();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tanxinzheng@139.com");
        message.setTo("277303310@qq.com");
        message.setSubject("测试邮件标题");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }

}