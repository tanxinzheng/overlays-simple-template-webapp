package com.xmomen.module.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 17/8/28.
 */
@RestController
public class MailController {

    @Autowired
    MailSender mailSender;

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/access/mail", method = RequestMethod.GET)
    public void getNotificationById(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tanxinzheng@139.com");
        message.setTo("277303310@qq.com");
        message.setSubject("测试邮件标题");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }

}
