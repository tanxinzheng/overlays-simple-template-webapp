package com.xmomen.framework.web.json;

import com.xmomen.module.attachment.service.AttachmentService;
import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.notification.model.NotificationTemplateModel;
import com.xmomen.module.notification.model.NotificationTemplateQuery;
import com.xmomen.module.notification.service.NotificationTemplateService;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tanxinzheng on 16/10/20.
 */
@Component
public class DefaultDictionaryInterpreterService implements DictionaryInterpreterService {

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    UserService userService;
    @Autowired
    AttachmentService attachmentService;

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Autowired
    HttpServletRequest request;

    @Autowired
    NotificationTemplateService notificationTemplateService;

    /**
     * 翻译
     *
     * @param dictionaryType 字典类型
     * @param dictionaryCode 字典代码
     * @return
     */
    @Override
    public String translate(DictionaryIndex dictionaryType, String dictionaryCode) {
        if(dictionaryType.equals(DictionaryIndex.USER_ID)){
            User user = userService.getOneUserCache(dictionaryCode);
            if(user == null){
                return null;
            }
            return user.getNickname();
        }else if(dictionaryType.equals(DictionaryIndex.ATTACHMENT_KEY)){
            Authentication subject = SecurityContextHolder.getContext().getAuthentication();
            String token = (String) subject.getCredentials();
            String contextPath = request.getScheme() +"://" + request.getServerName()  + ":" +request.getServerPort() +request.getContextPath();
            String fileUrl = contextPath + "/file/download?fileKey=" + dictionaryCode + "&token=" + token;
            return fileUrl;
        }else if(dictionaryType.equals(DictionaryIndex.NOTIFICATION_TEMPLATE)){
            NotificationTemplateQuery notificationTemplateQuery = new NotificationTemplateQuery();
            notificationTemplateQuery.setTemplateCode(dictionaryCode);
            NotificationTemplateModel notificationTemplateModel = notificationTemplateService.getOneNotificationTemplateModel(notificationTemplateQuery);
            if(notificationTemplateModel == null){
                return null;
            }
            return notificationTemplateModel.getTemplateName();
        }
        DictionaryQuery dictionaryQuery = new DictionaryQuery();
        dictionaryQuery.setCode(dictionaryCode);
        dictionaryQuery.setType(dictionaryType.name());
        DictionaryModel dictionaryModel = dictionaryService.getOneDictionaryModel(dictionaryQuery);
        if(dictionaryModel != null){
            return dictionaryModel.getDictionaryName();
        }
        return null;
    }
}
