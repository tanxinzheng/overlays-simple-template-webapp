package com.xmomen.module.authorization.service.impl;

import com.xmomen.module.authorization.entity.UserPermission;
import com.xmomen.module.authorization.entity.UserPermissionExample;
import com.xmomen.module.authorization.mapper.UserPermissionMapperExt;
import com.xmomen.module.authorization.model.UserPermissionCreate;
import com.xmomen.module.authorization.model.UserPermissionQuery;
import com.xmomen.module.authorization.model.UserPermissionUpdate;
import com.xmomen.module.authorization.model.UserPermissionModel;
import com.xmomen.module.authorization.service.UserPermissionService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增用户权限
     *
     * @param userPermissionCreate 新增用户权限对象参数
     * @return UserPermissionModel    用户权限领域对象
     */
    @Override
    @Transactional
    public UserPermissionModel createUserPermission(UserPermissionCreate userPermissionCreate) {
        UserPermission userPermission = createUserPermission(userPermissionCreate.getEntity());
        if(userPermission != null){
            return getOneUserPermissionModel(userPermission.getId());
        }
        return null;
    }

    /**
     * 新增用户权限实体对象
     *
     * @param userPermission 新增用户权限实体对象参数
     * @return UserPermission 用户权限实体对象
     */
    @Override
    @Transactional
    public UserPermission createUserPermission(UserPermission userPermission) {
        return mybatisDao.insertByModel(userPermission);
    }

    /**
     * 更新用户权限
     *
     * @param userPermissionUpdate 更新用户权限对象参数
     */
    @Override
    @Transactional
    public void updateUserPermission(UserPermissionUpdate userPermissionUpdate) {
        mybatisDao.update(userPermissionUpdate.getEntity());
    }

    /**
     * 更新用户权限实体对象
     *
     * @param userPermission 新增用户权限实体对象参数
     * @return UserPermission 用户权限实体对象
     */
    @Override
    @Transactional
    public void updateUserPermission(UserPermission userPermission) {
        mybatisDao.update(userPermission);
    }

    /**
     * 删除用户权限
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteUserPermission(String[] ids) {
        UserPermissionExample userPermissionExample = new UserPermissionExample();
        userPermissionExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(userPermissionExample);
    }

    /**
    * 删除用户权限
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteUserPermission(String id) {
        mybatisDao.deleteByPrimaryKey(UserPermission.class, id);
    }

    /**
     * 查询用户权限领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param userPermissionQuery 查询参数
     * @return Page<UserPermissionModel>   用户权限参数对象
     */
    @Override
    public Page<UserPermissionModel> getUserPermissionModelPage(int limit, int offset, UserPermissionQuery userPermissionQuery) {
        return (Page<UserPermissionModel>) mybatisDao.selectPage(UserPermissionMapperExt.UserPermissionMapperNameSpace + "getUserPermissionModel", userPermissionQuery, limit, offset);
    }

    /**
     * 查询用户权限领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<UserPermissionModel> 用户权限领域对象
     */
    @Override
    public Page<UserPermissionModel> getUserPermissionModelPage(int limit, int offset) {
        return (Page<UserPermissionModel>) mybatisDao.selectPage(UserPermissionMapperExt.UserPermissionMapperNameSpace + "getUserPermissionModel", null, limit, offset);
    }

    /**
     * 查询用户权限领域集合对象（带参数条件）
     *
     * @param userPermissionQuery 查询参数对象
     * @return List<UserPermissionModel> 用户权限领域集合对象
     */
    @Override
    public List<UserPermissionModel> getUserPermissionModelList(UserPermissionQuery userPermissionQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(UserPermissionMapperExt.UserPermissionMapperNameSpace + "getUserPermissionModel", userPermissionQuery);
    }

    /**
     * 查询用户权限领域集合对象（无参数条件）
     *
     * @return List<UserPermissionModel> 用户权限领域集合对象
     */
    @Override
    public List<UserPermissionModel> getUserPermissionModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(UserPermissionMapperExt.UserPermissionMapperNameSpace + "getUserPermissionModel");
    }

    /**
     * 查询用户权限实体对象
     *
     * @param id 主键
     * @return UserPermission 用户权限实体对象
     */
    @Override
    public UserPermission getOneUserPermission(String id) {
        return mybatisDao.selectByPrimaryKey(UserPermission.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return UserPermissionModel 用户权限领域对象
     */
    @Override
    public UserPermissionModel getOneUserPermissionModel(String id) {
        UserPermissionQuery userPermissionQuery = new UserPermissionQuery();
        userPermissionQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(UserPermissionMapperExt.UserPermissionMapperNameSpace + "getUserPermissionModel", userPermissionQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param userPermissionQuery 用户权限查询参数对象
     * @return UserPermissionModel 用户权限领域对象
     */
    @Override
    public UserPermissionModel getOneUserPermissionModel(UserPermissionQuery userPermissionQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(UserPermissionMapperExt.UserPermissionMapperNameSpace + "getUserPermissionModel", userPermissionQuery);
    }
}
