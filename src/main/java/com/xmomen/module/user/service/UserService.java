package com.xmomen.module.user.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.user.model.UserQuery;
import com.xmomen.module.user.model.UserModel;
import com.xmomen.module.user.model.User;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-16 22:59:54
 * @version 1.0.0
 */
public interface UserService {

    /**
     * 新增数据字典
     * @param  userModel   新增数据字典对象参数
     * @return  UserModel    数据字典领域对象
     */
    public UserModel createUser(UserModel userModel);

    /**
     * 新增数据字典实体对象
     * @param   user 新增数据字典实体对象参数
     * @return  User 数据字典实体对象
     */
    public User createUser(User user);

    /**
    * 批量新增数据字典
    * @param userModels     新增数据字典对象集合参数
    * @return List<UserModel>    数据字典领域对象集合
    */
    List<UserModel> createUsers(List<UserModel> userModels);

    /**
    * 更新数据字典
    *
    * @param userModel 更新数据字典对象参数
    * @param userQuery 过滤数据字典对象参数
    */
    public void updateUser(UserModel userModel, UserQuery userQuery);

    /**
     * 更新数据字典
     * @param userModel    更新数据字典对象参数
     */
    public void updateUser(UserModel userModel);

    /**
     * 更新数据字典实体对象
     * @param   user 新增数据字典实体对象参数
     * @return  User 数据字典实体对象
     */
    public void updateUser(User user);

    /**
     * 批量删除数据字典
     * @param ids   主键数组
     */
    public void deleteUser(String[] ids);

    /**
     * 删除数据字典
     * @param id   主键
     */
    public void deleteUser(String id);

    /**
     * 查询数据字典领域分页对象（带参数条件）
     * @param userQuery 查询参数
     * @return Page<UserModel>   数据字典参数对象
     */
    public Page<UserModel> getUserModelPage(UserQuery userQuery);

    /**
     * 查询数据字典领域集合对象（带参数条件）
     * @param userQuery 查询参数对象
     * @return List<UserModel> 数据字典领域集合对象
     */
    public List<UserModel> getUserModelList(UserQuery userQuery);

    /**
     * 查询数据字典实体对象
     * @param id 主键
     * @return User 数据字典实体对象
     */
    public User getOneUser(String id);

    /**
     * 根据username查询用户
     * @param username
     * @return
     */
    public UserModel getOneUserModelByUsername(String username);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return UserModel 数据字典领域对象
     */
    public UserModel getOneUserModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param userQuery 数据字典查询参数对象
     * @return UserModel 数据字典领域对象
     */
    public UserModel getOneUserModel(UserQuery userQuery) throws TooManyResultsException;
}
