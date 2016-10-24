package com.xmomen.module.notification.service.impl;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.notification.entity.NotificationText;
import com.xmomen.module.notification.entity.NotificationTextExample;
import com.xmomen.module.notification.mapper.NotificationTextMapperExt;
import com.xmomen.module.notification.model.NotificationTextCreate;
import com.xmomen.module.notification.model.NotificationTextModel;
import com.xmomen.module.notification.model.NotificationTextQuery;
import com.xmomen.module.notification.model.NotificationTextUpdate;
import com.xmomen.module.notification.service.NotificationTextService;
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
public class NotificationTextServiceImpl implements NotificationTextService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增通知内容
     *
     * @param notificationTextCreate 新增通知内容对象参数
     * @return NotificationTextModel    通知内容领域对象
     */
    @Override
    @Transactional
    public NotificationTextModel createNotificationText(NotificationTextCreate notificationTextCreate) {
        NotificationText notificationText = createNotificationText(notificationTextCreate.getEntity());
        if(notificationText != null){
            return getOneNotificationTextModel(notificationText.getId());
        }
        return null;
    }

    /**
     * 新增通知内容实体对象
     *
     * @param notificationText 新增通知内容实体对象参数
     * @return NotificationText 通知内容实体对象
     */
    @Override
    @Transactional
    public NotificationText createNotificationText(NotificationText notificationText) {
        return mybatisDao.insertByModel(notificationText);
    }

    /**
     * 更新通知内容
     *
     * @param notificationTextUpdate 更新通知内容对象参数
     */
    @Override
    @Transactional
    public void updateNotificationText(NotificationTextUpdate notificationTextUpdate) {
        mybatisDao.update(notificationTextUpdate.getEntity());
    }

    /**
     * 更新通知内容实体对象
     *
     * @param notificationText 新增通知内容实体对象参数
     * @return NotificationText 通知内容实体对象
     */
    @Override
    @Transactional
    public void updateNotificationText(NotificationText notificationText) {
        mybatisDao.update(notificationText);
    }

    /**
     * 删除通知内容
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteNotificationText(String[] ids) {
        NotificationTextExample notificationTextExample = new NotificationTextExample();
        notificationTextExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(notificationTextExample);
    }

    /**
    * 删除通知内容
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteNotificationText(String id) {
        mybatisDao.deleteByPrimaryKey(NotificationText.class, id);
    }

    /**
     * 查询通知内容领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param notificationTextQuery 查询参数
     * @return Page<NotificationTextModel>   通知内容参数对象
     */
    @Override
    public Page<NotificationTextModel> getNotificationTextModelPage(int limit, int offset, NotificationTextQuery notificationTextQuery) {
        return (Page<NotificationTextModel>) mybatisDao.selectPage(NotificationTextMapperExt.NotificationTextMapperNameSpace + "getNotificationTextModel", notificationTextQuery, limit, offset);
    }

    /**
     * 查询通知内容领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<NotificationTextModel> 通知内容领域对象
     */
    @Override
    public Page<NotificationTextModel> getNotificationTextModelPage(int limit, int offset) {
        return (Page<NotificationTextModel>) mybatisDao.selectPage(NotificationTextMapperExt.NotificationTextMapperNameSpace + "getNotificationTextModel", null, limit, offset);
    }

    /**
     * 查询通知内容领域集合对象（带参数条件）
     *
     * @param notificationTextQuery 查询参数对象
     * @return List<NotificationTextModel> 通知内容领域集合对象
     */
    @Override
    public List<NotificationTextModel> getNotificationTextModelList(NotificationTextQuery notificationTextQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(NotificationTextMapperExt.NotificationTextMapperNameSpace + "getNotificationTextModel", notificationTextQuery);
    }

    /**
     * 查询通知内容领域集合对象（无参数条件）
     *
     * @return List<NotificationTextModel> 通知内容领域集合对象
     */
    @Override
    public List<NotificationTextModel> getNotificationTextModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(NotificationTextMapperExt.NotificationTextMapperNameSpace + "getNotificationTextModel");
    }

    /**
     * 查询通知内容实体对象
     *
     * @param id 主键
     * @return NotificationText 通知内容实体对象
     */
    @Override
    public NotificationText getOneNotificationText(String id) {
        return mybatisDao.selectByPrimaryKey(NotificationText.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return NotificationTextModel 通知内容领域对象
     */
    @Override
    public NotificationTextModel getOneNotificationTextModel(String id) {
        NotificationTextQuery notificationTextQuery = new NotificationTextQuery();
        notificationTextQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(NotificationTextMapperExt.NotificationTextMapperNameSpace + "getNotificationTextModel", notificationTextQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param notificationTextQuery 通知内容查询参数对象
     * @return NotificationTextModel 通知内容领域对象
     */
    @Override
    public NotificationTextModel getOneNotificationTextModel(NotificationTextQuery notificationTextQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(NotificationTextMapperExt.NotificationTextMapperNameSpace + "getNotificationTextModel", notificationTextQuery);
    }
}
