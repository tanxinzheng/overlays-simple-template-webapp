package com.xmomen.module.notification.model;

import lombok.Data;
import org.springframework.mail.SimpleMailMessage;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/8/27.
 */
@Data
public class MailMessageModel extends SimpleMailMessage implements Serializable {

}
