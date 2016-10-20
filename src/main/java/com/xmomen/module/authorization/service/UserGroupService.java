package com.xmomen.module.authorization.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.authorization.model.UserGroupCreate;
import com.xmomen.module.authorization.model.UserGroupQuery;
import com.xmomen.module.authorization.model.UserGroupUpdate;
import com.xmomen.module.authorization.model.UserGroupModel;
import com.xmomen.module.authorization.entity.UserGroup;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:46:57
 * @version 1.0.0
 */
public interface UserGroupService {

    /**
     * 新增用户组
     * @param  userGroupCreate   新增用户组对象参数
     * @return  UserGroupModel    用户组领域对象
     */
    UserGroupModel createUserGroup(UserGroupCreate userGroupCreate);

    /**
     * 新增用户组实体对象
     * @param   userGroup 新增用户组实体对象参数
     * @return  UserGroup 用户组实体对象
     */
    UserGroup createUserGroup(UserGroup userGroup);

    /**
     * 更新用户组
     * @param userGroupUpdate    更新用户组对象参数
     */
    void updateUserGroup(UserGroupUpdate userGroupUpdate);

    /**
     * 更新用户组实体对象
     * @param   userGroup 新增用户组实体对象参数
     * @return  UserGroup 用户组实体对象
     */
    void updateUserGroup(UserGroup userGroup);

    /**
     * 批量删除用户组
     * @param ids   主键数组
     */
    void deleteUserGroup(String[] ids);

    /**
     * 删除用户组
     * @param id   主键
     */
    void deleteUserGroup(String id);

    /**
     * 查询用户组领域分页对象（带参数条件）
     * @param userGroupQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<UserGroupModel>   用户组参数对象
     */
    Page<UserGroupModel> getUserGroupModelPage(int limit, int offset, UserGroupQuery userGroupQuery);

    /**
     * 查询用户组领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<UserGroupModel> 用户组领域对象
     */
    Page<UserGroupModel> getUserGroupModelPage(int limit, int offset);

    /**
     * 查询用户组领域集合对象（带参数条件）
     * @param userGroupQuery 查询参数对象
     * @return List<UserGroupModel> 用户组领域集合对象
     */
    List<UserGroupModel> getUserGroupModelList(UserGroupQuery userGroupQuery);

    /**
     * 查询用户组领域集合对象（无参数条件）
     * @return List<UserGroupModel> 用户组领域集合对象
     */
    List<UserGroupModel> getUserGroupModelList();

    /**
     * 查询用户组实体对象
     * @param id 主键
     * @return UserGroup 用户组实体对象
     */
    UserGroup getOneUserGroup(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return UserGroupModel 用户组领域对象
     */
    UserGroupModel getOneUserGroupModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param userGroupQuery 用户组查询参数对象
     * @return UserGroupModel 用户组领域对象
     */
    UserGroupModel getOneUserGroupModel(UserGroupQuery userGroupQuery) throws TooManyResultsException;
}
