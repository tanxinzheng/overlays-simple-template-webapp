package com.xmomen.module.notification.service.impl;

import com.github.pagehelper.Page;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.module.notification.mapper.NotificationMapper;
import com.xmomen.module.notification.model.Notification;
import com.xmomen.module.notification.model.NotificationModel;
import com.xmomen.module.notification.model.NotificationQuery;
import com.xmomen.module.notification.model.NotificationStateCount;
import com.xmomen.module.notification.service.NotificationService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
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
public class NotificationServiceImpl implements NotificationService {

    private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    NotificationMapper notificationMapper;

    /**
     * 新增通知
     *
     * @param notificationModel 新增通知对象参数
     * @return NotificationModel    通知领域对象
     */
    @Override
    @Transactional
    public NotificationModel createNotification(NotificationModel notificationModel) {
        Notification notification = createNotification(notificationModel.getEntity());
        if(notification != null){
            return getOneNotificationModel(notification.getId());
        }
        return null;
    }

    /**
     * 新增通知实体对象
     *
     * @param notification 新增通知实体对象参数
     * @return Notification 通知实体对象
     */
    @Override
    @Transactional
    public Notification createNotification(Notification notification) {
        notificationMapper.insertSelective(notification);
        return notification;
    }

    /**
    * 批量新增通知
    *
    * @param notificationModels 新增通知对象集合参数
    * @return List<NotificationModel>    通知领域对象集合
    */
    @Override
    @Transactional
    public List<NotificationModel> createNotifications(List<NotificationModel> notificationModels) {
        List<NotificationModel> notificationModelList = null;
        for (NotificationModel notificationModel : notificationModels) {
            notificationModel = createNotification(notificationModel);
            if(notificationModel != null){
                if(notificationModelList == null){
                    notificationModelList = new ArrayList<>();
                }
                notificationModelList.add(notificationModel);
            }
        }
        return notificationModelList;
    }

    /**
    * 更新通知
    *
    * @param notificationModel 更新通知对象参数
    * @param notificationQuery 过滤通知对象参数
    */
    @Override
    @Transactional
    public void updateNotification(NotificationModel notificationModel, NotificationQuery notificationQuery) {
        notificationMapper.updateSelectiveByQuery(notificationModel.getEntity(), notificationQuery);
    }

    /**
     * 更新通知
     *
     * @param notificationModel 更新通知对象参数
     */
    @Override
    @Transactional
    public void updateNotification(NotificationModel notificationModel) {
        updateNotification(notificationModel.getEntity());
    }

    /**
     * 更新通知实体对象
     *
     * @param notification 新增通知实体对象参数
     * @return Notification 通知实体对象
     */
    @Override
    @Transactional
    public void updateNotification(Notification notification) {
        notificationMapper.updateSelective(notification);
    }

    /**
     * 删除通知
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteNotification(String[] ids) {
        notificationMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除通知
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteNotification(String id) {
        notificationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 通知状态统计
     *
     * @param notificationQuery
     */
    @Override
    public List<NotificationStateCount> countNotificationState(NotificationQuery notificationQuery) {
        return notificationMapper.countNotificationState(notificationQuery);
    }

    /**
     * 查询通知领域分页对象（带参数条件）
     *
     * @param notificationQuery 查询参数
     * @return Page<NotificationModel>   通知参数对象
     */
    @Override
    public Page<NotificationModel> getNotificationModelPage(NotificationQuery notificationQuery) {
        PageInterceptor.startPage(notificationQuery);
        notificationMapper.selectModel(notificationQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询通知领域集合对象（带参数条件）
     *
     * @param notificationQuery 查询参数对象
     * @return List<NotificationModel> 通知领域集合对象
     */
    @Override
    public List<NotificationModel> getNotificationModelList(NotificationQuery notificationQuery) {
        return notificationMapper.selectModel(notificationQuery);
    }

    /**
     * 查询通知实体对象
     *
     * @param id 主键
     * @return Notification 通知实体对象
     */
    @Override
    public Notification getOneNotification(String id) {
        return notificationMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return NotificationModel 通知领域对象
     */
    @Override
    public NotificationModel getOneNotificationModel(String id) {
        return notificationMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param notificationQuery 通知查询参数对象
     * @return NotificationModel 通知领域对象
     */
    @Override
    public NotificationModel getOneNotificationModel(NotificationQuery notificationQuery) throws TooManyResultsException {
        List<NotificationModel> notificationModelList = notificationMapper.selectModel(notificationQuery);
        if(CollectionUtils.isEmpty(notificationModelList)){
            return null;
        }
        if(notificationModelList.size() > 1){
            throw new TooManyResultsException();
        }
        return notificationModelList.get(0);
    }
}
