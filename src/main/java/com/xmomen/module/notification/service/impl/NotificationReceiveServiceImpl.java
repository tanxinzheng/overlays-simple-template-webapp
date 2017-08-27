package com.xmomen.module.notification.service.impl;

import com.github.pagehelper.Page;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.module.notification.mapper.NotificationReceiveMapper;
import com.xmomen.module.notification.model.*;
import com.xmomen.module.notification.service.NotificationReceiveService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@Service
public class NotificationReceiveServiceImpl implements NotificationReceiveService {

    private static Logger logger = LoggerFactory.getLogger(NotificationReceiveServiceImpl.class);

    @Autowired
    NotificationReceiveMapper notificationReceiveMapper;

    /**
     * 新增通知接收人
     *
     * @param notificationReceiveModel 新增通知接收人对象参数
     * @return NotificationReceiveModel    通知接收人领域对象
     */
    @Override
    @Transactional
    public NotificationReceiveModel createNotificationReceive(NotificationReceiveModel notificationReceiveModel) {
        NotificationReceive notificationReceive = createNotificationReceive(notificationReceiveModel.getEntity());
        if(notificationReceive != null){
            return getOneNotificationReceiveModel(notificationReceive.getId());
        }
        return null;
    }

    /**
     * 新增通知接收人实体对象
     *
     * @param notificationReceive 新增通知接收人实体对象参数
     * @return NotificationReceive 通知接收人实体对象
     */
    @Override
    @Transactional
    public NotificationReceive createNotificationReceive(NotificationReceive notificationReceive) {
        notificationReceiveMapper.insertSelective(notificationReceive);
        return notificationReceive;
    }

    /**
    * 批量新增通知接收人
    *
    * @param notificationReceiveModels 新增通知接收人对象集合参数
    * @return List<NotificationReceiveModel>    通知接收人领域对象集合
    */
    @Override
    @Transactional
    public List<NotificationReceiveModel> createNotificationReceives(List<NotificationReceiveModel> notificationReceiveModels) {
        List<NotificationReceiveModel> notificationReceiveModelList = null;
        for (NotificationReceiveModel notificationReceiveModel : notificationReceiveModels) {
            notificationReceiveModel = createNotificationReceive(notificationReceiveModel);
            if(notificationReceiveModel != null){
                if(notificationReceiveModelList == null){
                    notificationReceiveModelList = new ArrayList<>();
                }
                notificationReceiveModelList.add(notificationReceiveModel);
            }
        }
        return notificationReceiveModelList;
    }

    /**
    * 更新通知接收人
    *
    * @param notificationReceiveModel 更新通知接收人对象参数
    * @param notificationReceiveQuery 过滤通知接收人对象参数
    */
    @Override
    @Transactional
    public void updateNotificationReceive(NotificationReceiveModel notificationReceiveModel, NotificationReceiveQuery notificationReceiveQuery) {
        notificationReceiveMapper.updateSelectiveByQuery(notificationReceiveModel.getEntity(), notificationReceiveQuery);
    }

    /**
     * 更新通知接收人
     *
     * @param notificationReceiveModel 更新通知接收人对象参数
     */
    @Override
    @Transactional
    public void updateNotificationReceive(NotificationReceiveModel notificationReceiveModel) {
        updateNotificationReceive(notificationReceiveModel.getEntity());
    }

    /**
     * 更新通知接收人实体对象
     *
     * @param notificationReceive 新增通知接收人实体对象参数
     * @return NotificationReceive 通知接收人实体对象
     */
    @Override
    @Transactional
    public void updateNotificationReceive(NotificationReceive notificationReceive) {
        notificationReceiveMapper.updateSelective(notificationReceive);
    }

    /**
     * 删除通知接收人
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteNotificationReceive(String[] ids) {
        notificationReceiveMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除通知接收人
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteNotificationReceive(String id) {
        notificationReceiveMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询通知接收人领域分页对象（带参数条件）
     *
     * @param notificationReceiveQuery 查询参数
     * @return Page<NotificationReceiveModel>   通知接收人参数对象
     */
    @Override
    public Page<NotificationReceiveModel> getNotificationReceiveModelPage(NotificationReceiveQuery notificationReceiveQuery) {
        PageInterceptor.startPage(notificationReceiveQuery);
        notificationReceiveMapper.selectModel(notificationReceiveQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询通知接收人领域集合对象（带参数条件）
     *
     * @param notificationReceiveQuery 查询参数对象
     * @return List<NotificationReceiveModel> 通知接收人领域集合对象
     */
    @Override
    public List<NotificationReceiveModel> getNotificationReceiveModelList(NotificationReceiveQuery notificationReceiveQuery) {
        return notificationReceiveMapper.selectModel(notificationReceiveQuery);
    }

    /**
     * 查询通知接收人实体对象
     *
     * @param id 主键
     * @return NotificationReceive 通知接收人实体对象
     */
    @Override
    public NotificationReceive getOneNotificationReceive(String id) {
        return notificationReceiveMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return NotificationReceiveModel 通知接收人领域对象
     */
    @Override
    public NotificationReceiveModel getOneNotificationReceiveModel(String id) {
        return notificationReceiveMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param notificationReceiveQuery 通知接收人查询参数对象
     * @return NotificationReceiveModel 通知接收人领域对象
     */
    @Override
    public NotificationReceiveModel getOneNotificationReceiveModel(NotificationReceiveQuery notificationReceiveQuery) throws TooManyResultsException {
        List<NotificationReceiveModel> notificationReceiveModelList = notificationReceiveMapper.selectModel(notificationReceiveQuery);
        if(CollectionUtils.isEmpty(notificationReceiveModelList)){
            return null;
        }
        if(notificationReceiveModelList.size() > 1){
            throw new TooManyResultsException();
        }
        return notificationReceiveModelList.get(0);
    }

    /**
     * 查询接收人通知
     * @param notificationQuery
     * @return
     */
    @Override
    public Page<NotificationModel> selectNotification(NotificationQuery notificationQuery) {
        PageInterceptor.startPage(notificationQuery);
        notificationReceiveMapper.selectNotification(notificationQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询通知
     *
     * @param notificationId
     * @return
     */
    @Override
    public NotificationModel selectOneNotificationModel(String notificationId) {
        Assert.notNull(notificationId);
        NotificationQuery notificationQuery = new NotificationQuery();
        notificationQuery.setId(notificationId);
        List<NotificationModel> data = notificationReceiveMapper.selectNotification(notificationQuery);
        if(CollectionUtils.isEmpty(data)){
            return null;
        }
        return data.get(0);
    }

}
