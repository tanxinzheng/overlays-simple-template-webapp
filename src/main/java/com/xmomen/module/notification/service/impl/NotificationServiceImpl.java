package com.xmomen.module.notification.service.impl;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.notification.entity.Notification;
import com.xmomen.module.notification.entity.NotificationExample;
import com.xmomen.module.notification.mapper.NotificationMapperExt;
import com.xmomen.module.notification.model.NotificationCreate;
import com.xmomen.module.notification.model.NotificationModel;
import com.xmomen.module.notification.model.NotificationQuery;
import com.xmomen.module.notification.model.NotificationUpdate;
import com.xmomen.module.notification.service.NotificationService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增通知
     *
     * @param notificationCreate 新增通知对象参数
     * @return NotificationModel    通知领域对象
     */
    @Override
    @Transactional
    public NotificationModel createNotification(NotificationCreate notificationCreate) {
        Notification notification = createNotification(notificationCreate.getEntity());
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
        return mybatisDao.insertByModel(notification);
    }

    /**
     * 更新通知
     *
     * @param notificationUpdate 更新通知对象参数
     */
    @Override
    @Transactional
    public void updateNotification(NotificationUpdate notificationUpdate) {
        mybatisDao.update(notificationUpdate.getEntity());
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
        mybatisDao.update(notification);
    }

    /**
     * 删除通知
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteNotification(String[] ids) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(notificationExample);
    }

    /**
    * 删除通知
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteNotification(String id) {
        mybatisDao.deleteByPrimaryKey(Notification.class, id);
    }

    /**
     * 查询通知领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param notificationQuery 查询参数
     * @return Page<NotificationModel>   通知参数对象
     */
    @Override
    public Page<NotificationModel> getNotificationModelPage(int limit, int offset, NotificationQuery notificationQuery) {
        return (Page<NotificationModel>) mybatisDao.selectPage(NotificationMapperExt.NotificationMapperNameSpace + "getNotificationModel", notificationQuery, limit, offset);
    }

    /**
     * 查询通知领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<NotificationModel> 通知领域对象
     */
    @Override
    public Page<NotificationModel> getNotificationModelPage(int limit, int offset) {
        return (Page<NotificationModel>) mybatisDao.selectPage(NotificationMapperExt.NotificationMapperNameSpace + "getNotificationModel", null, limit, offset);
    }

    /**
     * 查询通知领域集合对象（带参数条件）
     *
     * @param notificationQuery 查询参数对象
     * @return List<NotificationModel> 通知领域集合对象
     */
    @Override
    public List<NotificationModel> getNotificationModelList(NotificationQuery notificationQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(NotificationMapperExt.NotificationMapperNameSpace + "getNotificationModel", notificationQuery);
    }

    /**
     * 查询通知领域集合对象（无参数条件）
     *
     * @return List<NotificationModel> 通知领域集合对象
     */
    @Override
    public List<NotificationModel> getNotificationModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(NotificationMapperExt.NotificationMapperNameSpace + "getNotificationModel");
    }

    /**
     * 查询通知实体对象
     *
     * @param id 主键
     * @return Notification 通知实体对象
     */
    @Override
    public Notification getOneNotification(String id) {
        return mybatisDao.selectByPrimaryKey(Notification.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return NotificationModel 通知领域对象
     */
    @Override
    public NotificationModel getOneNotificationModel(String id) {
        NotificationQuery notificationQuery = new NotificationQuery();
        notificationQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(NotificationMapperExt.NotificationMapperNameSpace + "getNotificationModel", notificationQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param notificationQuery 通知查询参数对象
     * @return NotificationModel 通知领域对象
     */
    @Override
    public NotificationModel getOneNotificationModel(NotificationQuery notificationQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(NotificationMapperExt.NotificationMapperNameSpace + "getNotificationModel", notificationQuery);
    }
}
