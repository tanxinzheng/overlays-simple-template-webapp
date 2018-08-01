package com.xmomen.module.mail.model;

import javafx.util.Pair;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tanxinzheng on 18/5/12.
 */
@Data
public class EmailModel implements Serializable {

    private String from;
    private String replyTo;
    @NotNull(message = "收件人不能为空")
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private Date sentDate;
    @NotBlank(message = "邮件标题不能为空")
    private String subject;
    @NotBlank(message = "邮件内容不能为空")
    private String text;
    private List<String> attachmentKeys;
    private List<Pair<String, InputStream>> attachmentList;
    private String templateCode;
    private Object templateData;
}
