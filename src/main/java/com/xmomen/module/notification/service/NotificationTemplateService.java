package com.xmomen.module.notification.service;

import com.github.pagehelper.Page;
import com.xmomen.module.notification.model.NotificationTemplateQuery;
import com.xmomen.module.notification.model.NotificationTemplateModel;
import com.xmomen.module.notification.model.NotificationTemplate;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public interface NotificationTemplateService {

    /**
     * 新增通知模板
     * @param  notificationTemplateModel   新增通知模板对象参数
     * @return  NotificationTemplateModel    通知模板领域对象
     */
    public NotificationTemplateModel createNotificationTemplate(NotificationTemplateModel notificationTemplateModel);

    /**
     * 新增通知模板实体对象
     * @param   notificationTemplate 新增通知模板实体对象参数
     * @return  NotificationTemplate 通知模板实体对象
     */
    public NotificationTemplate createNotificationTemplate(NotificationTemplate notificationTemplate);

    /**
    * 批量新增通知模板
    * @param notificationTemplateModels     新增通知模板对象集合参数
    * @return List<NotificationTemplateModel>    通知模板领域对象集合
    */
    List<NotificationTemplateModel> createNotificationTemplates(List<NotificationTemplateModel> notificationTemplateModels);

    /**
    * 更新通知模板
    *
    * @param notificationTemplateModel 更新通知模板对象参数
    * @param notificationTemplateQuery 过滤通知模板对象参数
    */
    public void updateNotificationTemplate(NotificationTemplateModel notificationTemplateModel, NotificationTemplateQuery notificationTemplateQuery);

    /**
     * 更新通知模板
     * @param notificationTemplateModel    更新通知模板对象参数
     */
    public void updateNotificationTemplate(NotificationTemplateModel notificationTemplateModel);

    /**
     * 更新通知模板实体对象
     * @param   notificationTemplate 新增通知模板实体对象参数
     * @return  NotificationTemplate 通知模板实体对象
     */
    public void updateNotificationTemplate(NotificationTemplate notificationTemplate);

    /**
     * 批量删除通知模板
     * @param ids   主键数组
     */
    public void deleteNotificationTemplate(String[] ids);

    /**
     * 删除通知模板
     * @param id   主键
     */
    public void deleteNotificationTemplate(String id);

    /**
     * 查询通知模板领域分页对象（带参数条件）
     * @param notificationTemplateQuery 查询参数
     * @return Page<NotificationTemplateModel>   通知模板参数对象
     */
    public Page<NotificationTemplateModel> getNotificationTemplateModelPage(NotificationTemplateQuery notificationTemplateQuery);

    /**
     * 查询通知模板领域集合对象（带参数条件）
     * @param notificationTemplateQuery 查询参数对象
     * @return List<NotificationTemplateModel> 通知模板领域集合对象
     */
    public List<NotificationTemplateModel> getNotificationTemplateModelList(NotificationTemplateQuery notificationTemplateQuery);

    /**
     * 查询通知模板实体对象
     * @param id 主键
     * @return NotificationTemplate 通知模板实体对象
     */
    public NotificationTemplate getOneNotificationTemplate(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return NotificationTemplateModel 通知模板领域对象
     */
    public NotificationTemplateModel getOneNotificationTemplateModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param notificationTemplateQuery 通知模板查询参数对象
     * @return NotificationTemplateModel 通知模板领域对象
     */
    public NotificationTemplateModel getOneNotificationTemplateModel(NotificationTemplateQuery notificationTemplateQuery);
}
