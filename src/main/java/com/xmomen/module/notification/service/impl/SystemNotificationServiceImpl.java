package com.xmomen.module.notification.service.impl;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.notification.entity.SystemNotification;
import com.xmomen.module.notification.entity.SystemNotificationExample;
import com.xmomen.module.notification.mapper.SystemNotificationMapperExt;
import com.xmomen.module.notification.model.SystemNotificationCreate;
import com.xmomen.module.notification.model.SystemNotificationModel;
import com.xmomen.module.notification.model.SystemNotificationQuery;
import com.xmomen.module.notification.model.SystemNotificationUpdate;
import com.xmomen.module.notification.service.SystemNotificationService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-22 23:34:58
 * @version 1.0.0
 */
@Service
public class SystemNotificationServiceImpl implements SystemNotificationService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增系统通知
     *
     * @param systemNotificationCreate 新增系统通知对象参数
     * @return SystemNotificationModel    系统通知领域对象
     */
    @Override
    @Transactional
    public SystemNotificationModel createSystemNotification(SystemNotificationCreate systemNotificationCreate) {
        SystemNotification systemNotification = createSystemNotification(systemNotificationCreate.getEntity());
        if(systemNotification != null){
            return getOneSystemNotificationModel(systemNotification.getId());
        }
        return null;
    }

    /**
     * 新增系统通知实体对象
     *
     * @param systemNotification 新增系统通知实体对象参数
     * @return SystemNotification 系统通知实体对象
     */
    @Override
    @Transactional
    public SystemNotification createSystemNotification(SystemNotification systemNotification) {
        return mybatisDao.insertByModel(systemNotification);
    }

    /**
     * 更新系统通知
     *
     * @param systemNotificationUpdate 更新系统通知对象参数
     */
    @Override
    @Transactional
    public void updateSystemNotification(SystemNotificationUpdate systemNotificationUpdate) {
        mybatisDao.update(systemNotificationUpdate.getEntity());
    }

    /**
     * 更新系统通知实体对象
     *
     * @param systemNotification 新增系统通知实体对象参数
     * @return SystemNotification 系统通知实体对象
     */
    @Override
    @Transactional
    public void updateSystemNotification(SystemNotification systemNotification) {
        mybatisDao.update(systemNotification);
    }

    /**
     * 删除系统通知
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteSystemNotification(String[] ids) {
        SystemNotificationExample systemNotificationExample = new SystemNotificationExample();
        systemNotificationExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(systemNotificationExample);
    }

    /**
    * 删除系统通知
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteSystemNotification(String id) {
        mybatisDao.deleteByPrimaryKey(SystemNotification.class, id);
    }

    /**
     * 查询系统通知领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param systemNotificationQuery 查询参数
     * @return Page<SystemNotificationModel>   系统通知参数对象
     */
    @Override
    public Page<SystemNotificationModel> getSystemNotificationModelPage(int limit, int offset, SystemNotificationQuery systemNotificationQuery) {
        return (Page<SystemNotificationModel>) mybatisDao.selectPage(SystemNotificationMapperExt.SystemNotificationMapperNameSpace + "getSystemNotificationModel", systemNotificationQuery, limit, offset);
    }

    /**
     * 查询系统通知领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<SystemNotificationModel> 系统通知领域对象
     */
    @Override
    public Page<SystemNotificationModel> getSystemNotificationModelPage(int limit, int offset) {
        return (Page<SystemNotificationModel>) mybatisDao.selectPage(SystemNotificationMapperExt.SystemNotificationMapperNameSpace + "getSystemNotificationModel", null, limit, offset);
    }

    /**
     * 查询系统通知领域集合对象（带参数条件）
     *
     * @param systemNotificationQuery 查询参数对象
     * @return List<SystemNotificationModel> 系统通知领域集合对象
     */
    @Override
    public List<SystemNotificationModel> getSystemNotificationModelList(SystemNotificationQuery systemNotificationQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(SystemNotificationMapperExt.SystemNotificationMapperNameSpace + "getSystemNotificationModel", systemNotificationQuery);
    }

    /**
     * 查询系统通知领域集合对象（无参数条件）
     *
     * @return List<SystemNotificationModel> 系统通知领域集合对象
     */
    @Override
    public List<SystemNotificationModel> getSystemNotificationModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(SystemNotificationMapperExt.SystemNotificationMapperNameSpace + "getSystemNotificationModel");
    }

    /**
     * 查询系统通知实体对象
     *
     * @param id 主键
     * @return SystemNotification 系统通知实体对象
     */
    @Override
    public SystemNotification getOneSystemNotification(String id) {
        return mybatisDao.selectByPrimaryKey(SystemNotification.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return SystemNotificationModel 系统通知领域对象
     */
    @Override
    public SystemNotificationModel getOneSystemNotificationModel(String id) {
        SystemNotificationQuery systemNotificationQuery = new SystemNotificationQuery();
        systemNotificationQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(SystemNotificationMapperExt.SystemNotificationMapperNameSpace + "getSystemNotificationModel", systemNotificationQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param systemNotificationQuery 系统通知查询参数对象
     * @return SystemNotificationModel 系统通知领域对象
     */
    @Override
    public SystemNotificationModel getOneSystemNotificationModel(SystemNotificationQuery systemNotificationQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(SystemNotificationMapperExt.SystemNotificationMapperNameSpace + "getSystemNotificationModel", systemNotificationQuery);
    }
}
