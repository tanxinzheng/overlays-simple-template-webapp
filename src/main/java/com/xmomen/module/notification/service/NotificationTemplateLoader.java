package com.xmomen.module.notification.service;

import com.xmomen.module.notification.model.NotificationTemplateModel;
import com.xmomen.module.notification.model.NotificationTemplateQuery;
import freemarker.cache.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by tanxinzheng on 2018/6/10.
 */
@Service
public class NotificationTemplateLoader implements TemplateLoader {

    @Autowired
    NotificationTemplateService notificationTemplateService;


    @Override
    public NotificationTemplateModel findTemplateSource(String templateCode) throws IOException {
        NotificationTemplateQuery notificationTemplateQuery = new NotificationTemplateQuery();
        notificationTemplateQuery.setTemplateCode(templateCode);
        NotificationTemplateModel notificationTemplateModel =
                notificationTemplateService.getOneNotificationTemplateModel(notificationTemplateQuery);
        if(notificationTemplateModel == null){
            return null;
        }
        return notificationTemplateModel;
    }

    @Override
    public long getLastModified(Object o) {
        return 0;
    }

    @Override
    public Reader getReader(Object o, String s) throws IOException {
        return null;
    }

    @Override
    public void closeTemplateSource(Object o) throws IOException {

    }
}
