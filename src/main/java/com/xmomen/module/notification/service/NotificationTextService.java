package com.xmomen.module.notification.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.notification.entity.NotificationText;
import com.xmomen.module.notification.model.NotificationTextCreate;
import com.xmomen.module.notification.model.NotificationTextModel;
import com.xmomen.module.notification.model.NotificationTextQuery;
import com.xmomen.module.notification.model.NotificationTextUpdate;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-22 23:34:58
 * @version 1.0.0
 */
public interface NotificationTextService {

    /**
     * 新增通知内容
     * @param  notificationTextCreate   新增通知内容对象参数
     * @return  NotificationTextModel    通知内容领域对象
     */
    NotificationTextModel createNotificationText(NotificationTextCreate notificationTextCreate);

    /**
     * 新增通知内容实体对象
     * @param   notificationText 新增通知内容实体对象参数
     * @return  NotificationText 通知内容实体对象
     */
    NotificationText createNotificationText(NotificationText notificationText);

    /**
     * 更新通知内容
     * @param notificationTextUpdate    更新通知内容对象参数
     */
    void updateNotificationText(NotificationTextUpdate notificationTextUpdate);

    /**
     * 更新通知内容实体对象
     * @param   notificationText 新增通知内容实体对象参数
     * @return  NotificationText 通知内容实体对象
     */
    void updateNotificationText(NotificationText notificationText);

    /**
     * 批量删除通知内容
     * @param ids   主键数组
     */
    void deleteNotificationText(String[] ids);

    /**
     * 删除通知内容
     * @param id   主键
     */
    void deleteNotificationText(String id);

    /**
     * 查询通知内容领域分页对象（带参数条件）
     * @param notificationTextQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<NotificationTextModel>   通知内容参数对象
     */
    Page<NotificationTextModel> getNotificationTextModelPage(int limit, int offset, NotificationTextQuery notificationTextQuery);

    /**
     * 查询通知内容领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<NotificationTextModel> 通知内容领域对象
     */
    Page<NotificationTextModel> getNotificationTextModelPage(int limit, int offset);

    /**
     * 查询通知内容领域集合对象（带参数条件）
     * @param notificationTextQuery 查询参数对象
     * @return List<NotificationTextModel> 通知内容领域集合对象
     */
    List<NotificationTextModel> getNotificationTextModelList(NotificationTextQuery notificationTextQuery);

    /**
     * 查询通知内容领域集合对象（无参数条件）
     * @return List<NotificationTextModel> 通知内容领域集合对象
     */
    List<NotificationTextModel> getNotificationTextModelList();

    /**
     * 查询通知内容实体对象
     * @param id 主键
     * @return NotificationText 通知内容实体对象
     */
    NotificationText getOneNotificationText(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return NotificationTextModel 通知内容领域对象
     */
    NotificationTextModel getOneNotificationTextModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param notificationTextQuery 通知内容查询参数对象
     * @return NotificationTextModel 通知内容领域对象
     */
    NotificationTextModel getOneNotificationTextModel(NotificationTextQuery notificationTextQuery) throws TooManyResultsException;
}
