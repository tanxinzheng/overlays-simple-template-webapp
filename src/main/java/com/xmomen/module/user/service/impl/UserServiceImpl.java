package com.xmomen.module.user.service.impl;

import com.xmomen.module.user.entity.User;
import com.xmomen.module.user.entity.UserExample;
import com.xmomen.module.user.mapper.UserMapperExt;
import com.xmomen.module.user.model.UserCreate;
import com.xmomen.module.user.model.UserQuery;
import com.xmomen.module.user.model.UserUpdate;
import com.xmomen.module.user.model.UserModel;
import com.xmomen.module.user.service.UserService;
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
 * @date    2016-10-20 1:05:48
 * @version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增用户
     *
     * @param userCreate 新增用户对象参数
     * @return UserModel    用户领域对象
     */
    @Override
    @Transactional
    public UserModel createUser(UserCreate userCreate) {
        User user = createUser(userCreate.getEntity());
        if(user != null){
            return getOneUserModel(user.getId());
        }
        return null;
    }

    /**
     * 新增用户实体对象
     *
     * @param user 新增用户实体对象参数
     * @return User 用户实体对象
     */
    @Override
    @Transactional
    public User createUser(User user) {
        return mybatisDao.insertByModel(user);
    }

    /**
     * 更新用户
     *
     * @param userUpdate 更新用户对象参数
     */
    @Override
    @Transactional
    public void updateUser(UserUpdate userUpdate) {
        mybatisDao.update(userUpdate.getEntity());
    }

    /**
     * 更新用户实体对象
     *
     * @param user 新增用户实体对象参数
     * @return User 用户实体对象
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        mybatisDao.update(user);
    }

    /**
     * 删除用户
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteUser(String[] ids) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(userExample);
    }

    /**
    * 删除用户
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteUser(String id) {
        mybatisDao.deleteByPrimaryKey(User.class, id);
    }

    /**
     * 查询用户领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param userQuery 查询参数
     * @return Page<UserModel>   用户参数对象
     */
    @Override
    public Page<UserModel> getUserModelPage(int limit, int offset, UserQuery userQuery) {
        return (Page<UserModel>) mybatisDao.selectPage(UserMapperExt.UserMapperNameSpace + "getUserModel", userQuery, limit, offset);
    }

    /**
     * 查询用户领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<UserModel> 用户领域对象
     */
    @Override
    public Page<UserModel> getUserModelPage(int limit, int offset) {
        return (Page<UserModel>) mybatisDao.selectPage(UserMapperExt.UserMapperNameSpace + "getUserModel", null, limit, offset);
    }

    /**
     * 查询用户领域集合对象（带参数条件）
     *
     * @param userQuery 查询参数对象
     * @return List<UserModel> 用户领域集合对象
     */
    @Override
    public List<UserModel> getUserModelList(UserQuery userQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(UserMapperExt.UserMapperNameSpace + "getUserModel", userQuery);
    }

    /**
     * 查询用户领域集合对象（无参数条件）
     *
     * @return List<UserModel> 用户领域集合对象
     */
    @Override
    public List<UserModel> getUserModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(UserMapperExt.UserMapperNameSpace + "getUserModel");
    }

    /**
     * 查询用户实体对象
     *
     * @param id 主键
     * @return User 用户实体对象
     */
    @Override
    public User getOneUser(String id) {
        return mybatisDao.selectByPrimaryKey(User.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return UserModel 用户领域对象
     */
    @Override
    public UserModel getOneUserModel(String id) {
        UserQuery userQuery = new UserQuery();
        userQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(UserMapperExt.UserMapperNameSpace + "getUserModel", userQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param userQuery 用户查询参数对象
     * @return UserModel 用户领域对象
     */
    @Override
    public UserModel getOneUserModel(UserQuery userQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(UserMapperExt.UserMapperNameSpace + "getUserModel", userQuery);
    }
}
