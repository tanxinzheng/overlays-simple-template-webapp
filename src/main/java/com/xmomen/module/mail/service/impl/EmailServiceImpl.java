package com.xmomen.module.mail.service.impl;

import com.alibaba.druid.util.IOUtils;
import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.fss.FileStoreService;
import com.xmomen.framework.fss.model.FileStorageResult;
import com.xmomen.module.attachment.model.AttachmentModel;
import com.xmomen.module.attachment.model.AttachmentQuery;
import com.xmomen.module.attachment.service.AttachmentService;
import com.xmomen.module.mail.model.EmailModel;
import com.xmomen.module.mail.service.EmailService;
import com.xmomen.module.notification.model.NotificationTemplateModel;
import com.xmomen.module.notification.service.NotificationTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by tanxinzheng on 2018/6/10.
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;
    @Autowired
    private FreeMarkerConfigurer configurer;

    /**
     * 发送邮件
     *
     * @param emailModel
     */
    @Override
    public void sendEmail(EmailModel emailModel) {
        loadAttachmentKeys(emailModel);
        loadTemplate(emailModel);
        if(CollectionUtils.isNotEmpty(emailModel.getAttachmentList())){
            sendAttachmentMailMessage(emailModel);
        }else{
            sendSimpleMailMessage(emailModel);
        }
    }

    /**
     * 发送普通邮件
     * @param emailModel
     */
    private void sendSimpleMailMessage(@Valid EmailModel emailModel){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailFrom);
        simpleMailMessage.setTo(emailModel.getTo());
        simpleMailMessage.setBcc(emailModel.getBcc());
        simpleMailMessage.setCc(emailModel.getCc());
        simpleMailMessage.setReplyTo(emailModel.getReplyTo());
        simpleMailMessage.setSentDate(emailModel.getSentDate());
        simpleMailMessage.setSubject(emailModel.getSubject());
        simpleMailMessage.setText(emailModel.getText());
        try {
            mailSender.send(simpleMailMessage);
            log.debug("email message send success, message : {}", JSONObject.toJSONString(simpleMailMessage));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Autowired
    AttachmentService attachmentService;
    @Autowired
    FileStoreService fileStoreService;

    /**
     * 加载附件Key
     * @param emailModel
     */
    private void loadAttachmentKeys(EmailModel emailModel){
        if(CollectionUtils.isNotEmpty(emailModel.getAttachmentKeys())) {
            AttachmentQuery attachmentQuery = new AttachmentQuery();
            attachmentQuery.setAttachmentKeys(emailModel.getAttachmentKeys().toArray(new String[emailModel.getAttachmentKeys().size()]));
            List<AttachmentModel> attachmentModelList = attachmentService.getAttachmentModelList(attachmentQuery);
            if(CollectionUtils.isNotEmpty(attachmentModelList)){
                List<Pair<String, InputStream>> pairList = Lists.newArrayList();
                attachmentModelList.stream().forEach(attachmentModel -> {
                    FileStorageResult result = fileStoreService.getFile(attachmentModel.getFullKey());
                    if(result.isSuccess()){
                        Pair<String, InputStream> filePair = new Pair<>(attachmentModel.getOriginName(), result.getInputStream());
                        pairList.add(filePair);
                    }
                });
                emailModel.setAttachmentList(pairList);
            }
        }
    }

    /**
     * 发送附件邮件
     * @param emailModel
     */
    private void sendAttachmentMailMessage(@Valid EmailModel emailModel) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailFrom);
            helper.setTo(emailModel.getTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText());
            if(emailModel.getBcc() != null){
                helper.setBcc(emailModel.getBcc());
            }
            if(emailModel.getCc() != null){
                helper.setCc(emailModel.getCc());
            }
            if(emailModel.getReplyTo() != null){
                helper.setReplyTo(emailModel.getReplyTo());
            }
            if(emailModel.getSentDate() != null){
                helper.setSentDate(emailModel.getSentDate());
            }
            for (Pair<String, InputStream> pair : emailModel.getAttachmentList()) {
                helper.addAttachment(pair.getKey(), new ByteArrayResource(IOUtils.readByteArray(pair.getValue())));
            }
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("Email Message send failure, Message: {}", mimeMessage);
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Autowired
    NotificationTemplateLoader notificationTemplateLoader;


    /**
     * 加载模板内容
     * @param emailModel
     */
    private void loadTemplate(EmailModel emailModel){
        if (StringUtils.isBlank(emailModel.getTemplateCode())) {
            return;
        }
        NotificationTemplateModel notificationTemplateModel = null;
        try {
            notificationTemplateModel = notificationTemplateLoader.findTemplateSource(emailModel.getTemplateCode());
            if(notificationTemplateModel == null){
                return;
            }
            Configuration configuration = configurer.getConfiguration();
            StringTemplateLoader stl = new StringTemplateLoader();
            String templateBody = notificationTemplateModel.getTemplateBody();
            String templateTitle = notificationTemplateModel.getTemplateTitle();
            stl.putTemplate(notificationTemplateModel.getTemplateCode(), templateBody);
            stl.putTemplate(notificationTemplateModel.getTemplateCode()+ "_TITLE", templateTitle);
            configuration.setTemplateLoader(stl);
            Template template1 = configuration.getTemplate(notificationTemplateModel.getTemplateCode());
            Template template2 = configuration.getTemplate(notificationTemplateModel.getTemplateCode()+ "_TITLE");
            StringWriter writerBody= new StringWriter();
            StringWriter writerTitle = new StringWriter();
            template1.process(emailModel.getTemplateData(), writerBody);
            emailModel.setText(writerBody.toString());
            template2.process(emailModel.getTemplateData(), writerTitle);
            emailModel.setSubject(writerTitle.toString());
        } catch (IOException | TemplateException e) {
            log.error(e.getMessage(), e);
        }
    }
}
