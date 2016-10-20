package com.xmomen.module.authorization.service.impl;

import com.xmomen.module.authorization.entity.UserGroup;
import com.xmomen.module.authorization.entity.UserGroupExample;
import com.xmomen.module.authorization.mapper.UserGroupMapperExt;
import com.xmomen.module.authorization.model.UserGroupCreate;
import com.xmomen.module.authorization.model.UserGroupQuery;
import com.xmomen.module.authorization.model.UserGroupUpdate;
import com.xmomen.module.authorization.model.UserGroupModel;
import com.xmomen.module.authorization.service.UserGroupService;
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
 * @date    2016-10-18 23:46:57
 * @version 1.0.0
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增用户组
     *
     * @param userGroupCreate 新增用户组对象参数
     * @return UserGroupModel    用户组领域对象
     */
    @Override
    @Transactional
    public UserGroupModel createUserGroup(UserGroupCreate userGroupCreate) {
        UserGroup userGroup = createUserGroup(userGroupCreate.getEntity());
        if(userGroup != null){
            return getOneUserGroupModel(userGroup.getId());
        }
        return null;
    }

    /**
     * 新增用户组实体对象
     *
     * @param userGroup 新增用户组实体对象参数
     * @return UserGroup 用户组实体对象
     */
    @Override
    @Transactional
    public UserGroup createUserGroup(UserGroup userGroup) {
        return mybatisDao.insertByModel(userGroup);
    }

    /**
     * 更新用户组
     *
     * @param userGroupUpdate 更新用户组对象参数
     */
    @Override
    @Transactional
    public void updateUserGroup(UserGroupUpdate userGroupUpdate) {
        mybatisDao.update(userGroupUpdate.getEntity());
    }

    /**
     * 更新用户组实体对象
     *
     * @param userGroup 新增用户组实体对象参数
     * @return UserGroup 用户组实体对象
     */
    @Override
    @Transactional
    public void updateUserGroup(UserGroup userGroup) {
        mybatisDao.update(userGroup);
    }

    /**
     * 删除用户组
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteUserGroup(String[] ids) {
        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(userGroupExample);
    }

    /**
    * 删除用户组
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteUserGroup(String id) {
        mybatisDao.deleteByPrimaryKey(UserGroup.class, id);
    }

    /**
     * 查询用户组领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param userGroupQuery 查询参数
     * @return Page<UserGroupModel>   用户组参数对象
     */
    @Override
    public Page<UserGroupModel> getUserGroupModelPage(int limit, int offset, UserGroupQuery userGroupQuery) {
        return (Page<UserGroupModel>) mybatisDao.selectPage(UserGroupMapperExt.UserGroupMapperNameSpace + "getUserGroupModel", userGroupQuery, limit, offset);
    }

    /**
     * 查询用户组领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<UserGroupModel> 用户组领域对象
     */
    @Override
    public Page<UserGroupModel> getUserGroupModelPage(int limit, int offset) {
        return (Page<UserGroupModel>) mybatisDao.selectPage(UserGroupMapperExt.UserGroupMapperNameSpace + "getUserGroupModel", null, limit, offset);
    }

    /**
     * 查询用户组领域集合对象（带参数条件）
     *
     * @param userGroupQuery 查询参数对象
     * @return List<UserGroupModel> 用户组领域集合对象
     */
    @Override
    public List<UserGroupModel> getUserGroupModelList(UserGroupQuery userGroupQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(UserGroupMapperExt.UserGroupMapperNameSpace + "getUserGroupModel", userGroupQuery);
    }

    /**
     * 查询用户组领域集合对象（无参数条件）
     *
     * @return List<UserGroupModel> 用户组领域集合对象
     */
    @Override
    public List<UserGroupModel> getUserGroupModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(UserGroupMapperExt.UserGroupMapperNameSpace + "getUserGroupModel");
    }

    /**
     * 查询用户组实体对象
     *
     * @param id 主键
     * @return UserGroup 用户组实体对象
     */
    @Override
    public UserGroup getOneUserGroup(String id) {
        return mybatisDao.selectByPrimaryKey(UserGroup.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return UserGroupModel 用户组领域对象
     */
    @Override
    public UserGroupModel getOneUserGroupModel(String id) {
        UserGroupQuery userGroupQuery = new UserGroupQuery();
        userGroupQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(UserGroupMapperExt.UserGroupMapperNameSpace + "getUserGroupModel", userGroupQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param userGroupQuery 用户组查询参数对象
     * @return UserGroupModel 用户组领域对象
     */
    @Override
    public UserGroupModel getOneUserGroupModel(UserGroupQuery userGroupQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(UserGroupMapperExt.UserGroupMapperNameSpace + "getUserGroupModel", userGroupQuery);
    }
}
