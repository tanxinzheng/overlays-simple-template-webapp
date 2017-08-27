package com.xmomen.module.notification.service;

import com.github.pagehelper.Page;
import com.xmomen.module.notification.model.NotificationSendQuery;
import com.xmomen.module.notification.model.NotificationSendModel;
import com.xmomen.module.notification.model.NotificationSend;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public interface NotificationSendService {

    /**
     * 新增通知发送人
     * @param  notificationSendModel   新增通知发送人对象参数
     * @return  NotificationSendModel    通知发送人领域对象
     */
    public NotificationSendModel createNotificationSend(NotificationSendModel notificationSendModel);

    /**
     * 新增通知发送人实体对象
     * @param   notificationSend 新增通知发送人实体对象参数
     * @return  NotificationSend 通知发送人实体对象
     */
    public NotificationSend createNotificationSend(NotificationSend notificationSend);

    /**
    * 批量新增通知发送人
    * @param notificationSendModels     新增通知发送人对象集合参数
    * @return List<NotificationSendModel>    通知发送人领域对象集合
    */
    List<NotificationSendModel> createNotificationSends(List<NotificationSendModel> notificationSendModels);

    /**
    * 更新通知发送人
    *
    * @param notificationSendModel 更新通知发送人对象参数
    * @param notificationSendQuery 过滤通知发送人对象参数
    */
    public void updateNotificationSend(NotificationSendModel notificationSendModel, NotificationSendQuery notificationSendQuery);

    /**
     * 更新通知发送人
     * @param notificationSendModel    更新通知发送人对象参数
     */
    public void updateNotificationSend(NotificationSendModel notificationSendModel);

    /**
     * 更新通知发送人实体对象
     * @param   notificationSend 新增通知发送人实体对象参数
     * @return  NotificationSend 通知发送人实体对象
     */
    public void updateNotificationSend(NotificationSend notificationSend);

    /**
     * 批量删除通知发送人
     * @param ids   主键数组
     */
    public void deleteNotificationSend(String[] ids);

    /**
     * 删除通知发送人
     * @param id   主键
     */
    public void deleteNotificationSend(String id);

    /**
     * 查询通知发送人领域分页对象（带参数条件）
     * @param notificationSendQuery 查询参数
     * @return Page<NotificationSendModel>   通知发送人参数对象
     */
    public Page<NotificationSendModel> getNotificationSendModelPage(NotificationSendQuery notificationSendQuery);

    /**
     * 查询通知发送人领域集合对象（带参数条件）
     * @param notificationSendQuery 查询参数对象
     * @return List<NotificationSendModel> 通知发送人领域集合对象
     */
    public List<NotificationSendModel> getNotificationSendModelList(NotificationSendQuery notificationSendQuery);

    /**
     * 查询通知发送人实体对象
     * @param id 主键
     * @return NotificationSend 通知发送人实体对象
     */
    public NotificationSend getOneNotificationSend(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return NotificationSendModel 通知发送人领域对象
     */
    public NotificationSendModel getOneNotificationSendModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param notificationSendQuery 通知发送人查询参数对象
     * @return NotificationSendModel 通知发送人领域对象
     */
    public NotificationSendModel getOneNotificationSendModel(NotificationSendQuery notificationSendQuery) throws TooManyResultsException;
}
