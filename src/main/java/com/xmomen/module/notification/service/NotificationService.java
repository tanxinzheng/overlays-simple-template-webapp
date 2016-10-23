package com.xmomen.module.notification.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.notification.entity.Notification;
import com.xmomen.module.notification.model.NotificationCreate;
import com.xmomen.module.notification.model.NotificationModel;
import com.xmomen.module.notification.model.NotificationQuery;
import com.xmomen.module.notification.model.NotificationUpdate;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-22 23:34:58
 * @version 1.0.0
 */
public interface NotificationService {

    /**
     * 新增通知
     * @param  notificationCreate   新增通知对象参数
     * @return  NotificationModel    通知领域对象
     */
    NotificationModel createNotification(NotificationCreate notificationCreate);

    /**
     * 新增通知实体对象
     * @param   notification 新增通知实体对象参数
     * @return  Notification 通知实体对象
     */
    Notification createNotification(Notification notification);

    /**
     * 更新通知
     * @param notificationUpdate    更新通知对象参数
     */
    void updateNotification(NotificationUpdate notificationUpdate);

    /**
     * 更新通知实体对象
     * @param   notification 新增通知实体对象参数
     * @return  Notification 通知实体对象
     */
    void updateNotification(Notification notification);

    /**
     * 批量删除通知
     * @param ids   主键数组
     */
    void deleteNotification(String[] ids);

    /**
     * 删除通知
     * @param id   主键
     */
    void deleteNotification(String id);

    /**
     * 查询通知领域分页对象（带参数条件）
     * @param notificationQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<NotificationModel>   通知参数对象
     */
    Page<NotificationModel> getNotificationModelPage(int limit, int offset, NotificationQuery notificationQuery);

    /**
     * 查询通知领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<NotificationModel> 通知领域对象
     */
    Page<NotificationModel> getNotificationModelPage(int limit, int offset);

    /**
     * 查询通知领域集合对象（带参数条件）
     * @param notificationQuery 查询参数对象
     * @return List<NotificationModel> 通知领域集合对象
     */
    List<NotificationModel> getNotificationModelList(NotificationQuery notificationQuery);

    /**
     * 查询通知领域集合对象（无参数条件）
     * @return List<NotificationModel> 通知领域集合对象
     */
    List<NotificationModel> getNotificationModelList();

    /**
     * 查询通知实体对象
     * @param id 主键
     * @return Notification 通知实体对象
     */
    Notification getOneNotification(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return NotificationModel 通知领域对象
     */
    NotificationModel getOneNotificationModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param notificationQuery 通知查询参数对象
     * @return NotificationModel 通知领域对象
     */
    NotificationModel getOneNotificationModel(NotificationQuery notificationQuery) throws TooManyResultsException;
}
