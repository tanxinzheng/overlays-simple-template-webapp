package com.xmomen.module.user.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.user.model.UserCreate;
import com.xmomen.module.user.model.UserQuery;
import com.xmomen.module.user.model.UserUpdate;
import com.xmomen.module.user.model.UserModel;
import com.xmomen.module.user.entity.User;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-13 0:13:58
 * @version 1.0.0
 */
public interface UserService {

    /**
     * 新增用户
     * @param  userCreate   新增用户对象参数
     * @return  UserModel    用户领域对象
     */
    public UserModel createUser(UserCreate userCreate);

    /**
     * 新增用户实体对象
     * @param   user 新增用户实体对象参数
     * @return  User 用户实体对象
     */
    public User createUser(User user);

    /**
     * 更新用户
     * @param userUpdate    更新用户对象参数
     */
    public void updateUser(UserUpdate userUpdate);

    /**
     * 更新用户实体对象
     * @param   user 新增用户实体对象参数
     * @return  User 用户实体对象
     */
    public void updateUser(User user);

    /**
     * 删除用户
     * @param ids   主键数组
     */
    public void deleteUser(String[] ids);

    /**
     * 查询用户领域分页对象（带参数条件）
     * @param userQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<UserModel>   用户参数对象
     */
    public Page<UserModel> getUserModelPage(int limit, int offset, UserQuery userQuery);

    /**
     * 查询用户领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<UserModel> 用户领域对象
     */
    public Page<UserModel> getUserModelPage(int limit, int offset);

    /**
     * 查询用户领域集合对象（带参数条件）
     * @param userQuery 查询参数对象
     * @return List<UserModel> 用户领域集合对象
     */
    public List<UserModel> getUserModelList(UserQuery userQuery);

    /**
     * 查询用户领域集合对象（无参数条件）
     * @return List<UserModel> 用户领域集合对象
     */
    public List<UserModel> getUserModelList();

    /**
     * 查询用户实体对象
     * @param id 主键
     * @return User 用户实体对象
     */
    public User getOneUser(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return UserModel 用户领域对象
     */
    public UserModel getOneUserModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param userQuery 用户查询参数对象
     * @return UserModel 用户领域对象
     */
    public UserModel getOneUserModel(UserQuery userQuery) throws TooManyResultsException;
}
