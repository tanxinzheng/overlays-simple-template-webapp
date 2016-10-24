package com.xmomen.module.notification.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.notification.entity.SystemNotification;
import com.xmomen.module.notification.model.SystemNotificationCreate;
import com.xmomen.module.notification.model.SystemNotificationModel;
import com.xmomen.module.notification.model.SystemNotificationQuery;
import com.xmomen.module.notification.model.SystemNotificationUpdate;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
public interface SystemNotificationService {

    /**
     * 新增系统通知
     * @param  systemNotificationCreate   新增系统通知对象参数
     * @return  SystemNotificationModel    系统通知领域对象
     */
    SystemNotificationModel createSystemNotification(SystemNotificationCreate systemNotificationCreate);

    /**
     * 新增系统通知实体对象
     * @param   systemNotification 新增系统通知实体对象参数
     * @return  SystemNotification 系统通知实体对象
     */
    SystemNotification createSystemNotification(SystemNotification systemNotification);

    /**
     * 更新系统通知
     * @param systemNotificationUpdate    更新系统通知对象参数
     */
    void updateSystemNotification(SystemNotificationUpdate systemNotificationUpdate);

    /**
     * 更新系统通知实体对象
     * @param   systemNotification 新增系统通知实体对象参数
     * @return  SystemNotification 系统通知实体对象
     */
    void updateSystemNotification(SystemNotification systemNotification);

    /**
     * 批量删除系统通知
     * @param ids   主键数组
     */
    void deleteSystemNotification(String[] ids);

    /**
     * 删除系统通知
     * @param id   主键
     */
    void deleteSystemNotification(String id);

    /**
     * 查询系统通知领域分页对象（带参数条件）
     * @param systemNotificationQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<SystemNotificationModel>   系统通知参数对象
     */
    Page<SystemNotificationModel> getSystemNotificationModelPage(int limit, int offset, SystemNotificationQuery systemNotificationQuery);

    /**
     * 查询系统通知领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<SystemNotificationModel> 系统通知领域对象
     */
    Page<SystemNotificationModel> getSystemNotificationModelPage(int limit, int offset);

    /**
     * 查询系统通知领域集合对象（带参数条件）
     * @param systemNotificationQuery 查询参数对象
     * @return List<SystemNotificationModel> 系统通知领域集合对象
     */
    List<SystemNotificationModel> getSystemNotificationModelList(SystemNotificationQuery systemNotificationQuery);

    /**
     * 查询系统通知领域集合对象（无参数条件）
     * @return List<SystemNotificationModel> 系统通知领域集合对象
     */
    List<SystemNotificationModel> getSystemNotificationModelList();

    /**
     * 查询系统通知实体对象
     * @param id 主键
     * @return SystemNotification 系统通知实体对象
     */
    SystemNotification getOneSystemNotification(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return SystemNotificationModel 系统通知领域对象
     */
    SystemNotificationModel getOneSystemNotificationModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param systemNotificationQuery 系统通知查询参数对象
     * @return SystemNotificationModel 系统通知领域对象
     */
    SystemNotificationModel getOneSystemNotificationModel(SystemNotificationQuery systemNotificationQuery) throws TooManyResultsException;
}
