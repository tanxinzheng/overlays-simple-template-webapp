package com.xmomen.module.notification.service;

import com.github.pagehelper.Page;
import com.xmomen.module.notification.model.*;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public interface NotificationReceiveService {

    /**
     * 新增通知接收人
     * @param  notificationReceiveModel   新增通知接收人对象参数
     * @return  NotificationReceiveModel    通知接收人领域对象
     */
    public NotificationReceiveModel createNotificationReceive(NotificationReceiveModel notificationReceiveModel);

    /**
     * 新增通知接收人实体对象
     * @param   notificationReceive 新增通知接收人实体对象参数
     * @return  NotificationReceive 通知接收人实体对象
     */
    public NotificationReceive createNotificationReceive(NotificationReceive notificationReceive);

    /**
    * 批量新增通知接收人
    * @param notificationReceiveModels     新增通知接收人对象集合参数
    * @return List<NotificationReceiveModel>    通知接收人领域对象集合
    */
    List<NotificationReceiveModel> createNotificationReceives(List<NotificationReceiveModel> notificationReceiveModels);

    /**
    * 更新通知接收人
    *
    * @param notificationReceiveModel 更新通知接收人对象参数
    * @param notificationReceiveQuery 过滤通知接收人对象参数
    */
    public void updateNotificationReceive(NotificationReceiveModel notificationReceiveModel, NotificationReceiveQuery notificationReceiveQuery);

    /**
     * 更新通知接收人
     * @param notificationReceiveModel    更新通知接收人对象参数
     */
    public void updateNotificationReceive(NotificationReceiveModel notificationReceiveModel);

    /**
     * 更新通知接收人实体对象
     * @param   notificationReceive 新增通知接收人实体对象参数
     * @return  NotificationReceive 通知接收人实体对象
     */
    public void updateNotificationReceive(NotificationReceive notificationReceive);

    /**
     * 批量删除通知接收人
     * @param ids   主键数组
     */
    public void deleteNotificationReceive(String[] ids);

    /**
     * 删除通知接收人
     * @param id   主键
     */
    public void deleteNotificationReceive(String id);

    /**
     * 查询通知接收人领域分页对象（带参数条件）
     * @param notificationReceiveQuery 查询参数
     * @return Page<NotificationReceiveModel>   通知接收人参数对象
     */
    public Page<NotificationReceiveModel> getNotificationReceiveModelPage(NotificationReceiveQuery notificationReceiveQuery);

    /**
     * 查询通知接收人领域集合对象（带参数条件）
     * @param notificationReceiveQuery 查询参数对象
     * @return List<NotificationReceiveModel> 通知接收人领域集合对象
     */
    public List<NotificationReceiveModel> getNotificationReceiveModelList(NotificationReceiveQuery notificationReceiveQuery);

    /**
     * 查询通知接收人实体对象
     * @param id 主键
     * @return NotificationReceive 通知接收人实体对象
     */
    public NotificationReceive getOneNotificationReceive(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return NotificationReceiveModel 通知接收人领域对象
     */
    public NotificationReceiveModel getOneNotificationReceiveModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param notificationReceiveQuery 通知接收人查询参数对象
     * @return NotificationReceiveModel 通知接收人领域对象
     */
    public NotificationReceiveModel getOneNotificationReceiveModel(NotificationReceiveQuery notificationReceiveQuery);

    /**
     * 查询接收人通知
     * @param notificationQuery
     * @return
     */
    public Page<NotificationModel> selectNotification(NotificationQuery notificationQuery);

    /**
     * 查询通知
     * @param notificationId
     * @return
     */
    public NotificationModel selectOneNotificationModel(String notificationId);
}
