package com.xmomen.module.notification.service.impl;

import com.github.pagehelper.Page;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.module.notification.mapper.NotificationSendMapper;
import com.xmomen.module.notification.model.*;
import com.xmomen.module.notification.service.NotificationReceiveService;
import com.xmomen.module.notification.service.NotificationSendService;
import com.xmomen.module.notification.service.NotificationService;
import com.xmomen.module.notification.service.NotificationTemplateService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@Service
public class NotificationSendServiceImpl implements NotificationSendService {

    private static Logger logger = LoggerFactory.getLogger(NotificationSendServiceImpl.class);

    @Autowired
    NotificationSendMapper notificationSendMapper;

    @Autowired
    NotificationTemplateService notificationTemplateService;

    @Autowired
    NotificationService NotificationService;

    @Autowired
    NotificationReceiveService notificationReceiveService;

    /**
     * 新增通知发送人
     *
     * @param notificationSendModel 新增通知发送人对象参数
     * @return NotificationSendModel    通知发送人领域对象
     */
    @Override
    @Transactional
    public NotificationSendModel createNotificationSend(NotificationSendModel notificationSendModel) {
        NotificationTemplateModel notificationTemplateModel =
                notificationTemplateService.getOneNotificationTemplateModel(new NotificationTemplateQuery(notificationSendModel.getTemplateCode()));
        // 构建消息
        Notification notification = new Notification();
        notification.setTitle(notificationTemplateModel.getTemplateTitle());
        notification.setBody(notificationTemplateModel.getTemplateBody());
        notification.setExpireTime(notificationSendModel.getExpireTime());
        notification.setNotificationType(notificationSendModel.getNotificationType());
        notification.setTemplateId(notificationTemplateModel.getId());
        NotificationService.createNotification(notification);
        for (String receiver : notificationSendModel.getReceivers()) {
            NotificationReceive notificationReceive = new NotificationReceive();
            notificationReceive.setDataState("UNREAD");
            notificationReceive.setNotificationId(notification.getId());
            notificationReceive.setReceiver(receiver);
            notificationReceiveService.createNotificationReceive(notificationReceive);
        }
        notificationSendModel.setNotificationId(notification.getId());
        NotificationSend notificationSend = createNotificationSend(notificationSendModel.getEntity());
        if(notificationSend != null){
            return getOneNotificationSendModel(notificationSend.getId());
        }
        return null;
    }

    /**
     * 新增通知发送人实体对象
     *
     * @param notificationSend 新增通知发送人实体对象参数
     * @return NotificationSend 通知发送人实体对象
     */
    @Override
    @Transactional
    public NotificationSend createNotificationSend(NotificationSend notificationSend) {
        notificationSendMapper.insertSelective(notificationSend);
        return notificationSend;
    }

    /**
    * 批量新增通知发送人
    *
    * @param notificationSendModels 新增通知发送人对象集合参数
    * @return List<NotificationSendModel>    通知发送人领域对象集合
    */
    @Override
    @Transactional
    public List<NotificationSendModel> createNotificationSends(List<NotificationSendModel> notificationSendModels) {
        List<NotificationSendModel> notificationSendModelList = null;
        for (NotificationSendModel notificationSendModel : notificationSendModels) {
            notificationSendModel = createNotificationSend(notificationSendModel);
            if(notificationSendModel != null){
                if(notificationSendModelList == null){
                    notificationSendModelList = new ArrayList<>();
                }
                notificationSendModelList.add(notificationSendModel);
            }
        }
        return notificationSendModelList;
    }

    /**
    * 更新通知发送人
    *
    * @param notificationSendModel 更新通知发送人对象参数
    * @param notificationSendQuery 过滤通知发送人对象参数
    */
    @Override
    @Transactional
    public void updateNotificationSend(NotificationSendModel notificationSendModel, NotificationSendQuery notificationSendQuery) {
        notificationSendMapper.updateSelectiveByQuery(notificationSendModel.getEntity(), notificationSendQuery);
    }

    /**
     * 更新通知发送人
     *
     * @param notificationSendModel 更新通知发送人对象参数
     */
    @Override
    @Transactional
    public void updateNotificationSend(NotificationSendModel notificationSendModel) {
        updateNotificationSend(notificationSendModel.getEntity());
    }

    /**
     * 更新通知发送人实体对象
     *
     * @param notificationSend 新增通知发送人实体对象参数
     * @return NotificationSend 通知发送人实体对象
     */
    @Override
    @Transactional
    public void updateNotificationSend(NotificationSend notificationSend) {
        notificationSendMapper.updateSelective(notificationSend);
    }

    /**
     * 删除通知发送人
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteNotificationSend(String[] ids) {
        notificationSendMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除通知发送人
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteNotificationSend(String id) {
        notificationSendMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询通知发送人领域分页对象（带参数条件）
     *
     * @param notificationSendQuery 查询参数
     * @return Page<NotificationSendModel>   通知发送人参数对象
     */
    @Override
    public Page<NotificationSendModel> getNotificationSendModelPage(NotificationSendQuery notificationSendQuery) {
        PageInterceptor.startPage(notificationSendQuery);
        notificationSendMapper.selectModel(notificationSendQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询通知发送人领域集合对象（带参数条件）
     *
     * @param notificationSendQuery 查询参数对象
     * @return List<NotificationSendModel> 通知发送人领域集合对象
     */
    @Override
    public List<NotificationSendModel> getNotificationSendModelList(NotificationSendQuery notificationSendQuery) {
        return notificationSendMapper.selectModel(notificationSendQuery);
    }

    /**
     * 查询通知发送人实体对象
     *
     * @param id 主键
     * @return NotificationSend 通知发送人实体对象
     */
    @Override
    public NotificationSend getOneNotificationSend(String id) {
        return notificationSendMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return NotificationSendModel 通知发送人领域对象
     */
    @Override
    public NotificationSendModel getOneNotificationSendModel(String id) {
        return notificationSendMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param notificationSendQuery 通知发送人查询参数对象
     * @return NotificationSendModel 通知发送人领域对象
     */
    @Override
    public NotificationSendModel getOneNotificationSendModel(NotificationSendQuery notificationSendQuery) {
        List<NotificationSendModel> notificationSendModelList = notificationSendMapper.selectModel(notificationSendQuery);
        if(CollectionUtils.isEmpty(notificationSendModelList)){
            return null;
        }
        if(notificationSendModelList.size() > 1){
            throw new BusinessException();
        }
        return notificationSendModelList.get(0);
    }
}
