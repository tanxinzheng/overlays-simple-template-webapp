package com.xmomen.module.notification.service;

import com.github.pagehelper.Page;
import com.xmomen.module.notification.model.Notification;
import com.xmomen.module.notification.model.NotificationModel;
import com.xmomen.module.notification.model.NotificationQuery;
import com.xmomen.module.notification.model.NotificationStateCount;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public interface NotificationService {

    /**
     * 新增通知
     * @param  notificationModel   新增通知对象参数
     * @return  NotificationModel    通知领域对象
     */
    public NotificationModel createNotification(NotificationModel notificationModel);

    /**
     * 新增通知实体对象
     * @param   notification 新增通知实体对象参数
     * @return  Notification 通知实体对象
     */
    public Notification createNotification(Notification notification);

    /**
    * 批量新增通知
    * @param notificationModels     新增通知对象集合参数
    * @return List<NotificationModel>    通知领域对象集合
    */
    List<NotificationModel> createNotifications(List<NotificationModel> notificationModels);

    /**
    * 更新通知
    *
    * @param notificationModel 更新通知对象参数
    * @param notificationQuery 过滤通知对象参数
    */
    public void updateNotification(NotificationModel notificationModel, NotificationQuery notificationQuery);

    /**
     * 更新通知
     * @param notificationModel    更新通知对象参数
     */
    public void updateNotification(NotificationModel notificationModel);

    /**
     * 更新通知实体对象
     * @param   notification 新增通知实体对象参数
     * @return  Notification 通知实体对象
     */
    public void updateNotification(Notification notification);

    /**
     * 批量删除通知
     * @param ids   主键数组
     */
    public void deleteNotification(String[] ids);

    /**
     * 删除通知
     * @param id   主键
     */
    public void deleteNotification(String id);

    /**
     * 通知状态统计
     * @param notificationQuery
     */
    List<NotificationStateCount> countNotificationState(NotificationQuery notificationQuery);

    /**
     * 查询通知领域分页对象（带参数条件）
     * @param notificationQuery 查询参数
     * @return Page<NotificationModel>   通知参数对象
     */
    public Page<NotificationModel> getNotificationModelPage(NotificationQuery notificationQuery);

    /**
     * 查询通知领域集合对象（带参数条件）
     * @param notificationQuery 查询参数对象
     * @return List<NotificationModel> 通知领域集合对象
     */
    public List<NotificationModel> getNotificationModelList(NotificationQuery notificationQuery);

    /**
     * 查询通知实体对象
     * @param id 主键
     * @return Notification 通知实体对象
     */
    public Notification getOneNotification(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return NotificationModel 通知领域对象
     */
    public NotificationModel getOneNotificationModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param notificationQuery 通知查询参数对象
     * @return NotificationModel 通知领域对象
     */
    public NotificationModel getOneNotificationModel(NotificationQuery notificationQuery) throws TooManyResultsException;
}
