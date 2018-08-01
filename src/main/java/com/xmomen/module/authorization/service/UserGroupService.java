package com.xmomen.module.authorization.service;

import com.github.pagehelper.Page;
import com.xmomen.module.authorization.model.*;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public interface UserGroupService {

    /**
     * 新增用户组关联
     * @param  userGroupModel   新增用户组关联对象参数
     * @return  UserGroupModel    用户组关联领域对象
     */
    public UserGroupModel createUserGroup(UserGroupModel userGroupModel);

    /**
     * 新增用户组关联实体对象
     * @param   userGroup 新增用户组关联实体对象参数
     * @return  UserGroup 用户组关联实体对象
     */
    public UserGroup createUserGroup(UserGroup userGroup);

    /**
     * 批量新增用户组关联
     * @param userId
     * @param groupIds
     */
    public void createUserGroups(String userId, String... groupIds);

    /**
     * 绑定用户至用户组
     * @param groupId
     * @param userIds
     */
    public void bindUsers2Group(String groupId, String... userIds);

    /**
     * 绑定用户组
     * @param userId
     * @param groupCode
     */
    public void createUserGroupByCode(String userId, String... groupCode);

    /**
    * 更新用户组关联
    *
    * @param userGroupModel 更新用户组关联对象参数
    * @param userGroupQuery 过滤用户组关联对象参数
    */
    public void updateUserGroup(UserGroupModel userGroupModel, UserGroupQuery userGroupQuery);

    /**
     * 更新用户组关联
     * @param userGroupModel    更新用户组关联对象参数
     */
    public void updateUserGroup(UserGroupModel userGroupModel);

    /**
     * 更新用户组关联实体对象
     * @param   userGroup 新增用户组关联实体对象参数
     * @return  UserGroup 用户组关联实体对象
     */
    public void updateUserGroup(UserGroup userGroup);

    /**
     * 批量删除用户组关联
     * @param ids   主键数组
     */
    public void deleteUserGroup(String[] ids);

    /**
     * 删除用户组关联
     * @param id   主键
     */
    public void deleteUserGroup(String id);

    /**
     * 查询用户组关联领域分页对象（带参数条件）
     * @param userGroupQuery 查询参数
     * @return Page<UserGroupModel>   用户组关联参数对象
     */
    public Page<UserGroupModel> getUserGroupModelPage(UserGroupQuery userGroupQuery);

    /**
     * 查询用户组关联领域集合对象（带参数条件）
     * @param userGroupQuery 查询参数对象
     * @return List<UserGroupModel> 用户组关联领域集合对象
     */
    public List<UserGroupModel> getUserGroupModelList(UserGroupQuery userGroupQuery);

    /**
     * 查询用户组关联实体对象
     * @param id 主键
     * @return UserGroup 用户组关联实体对象
     */
    public UserGroup getOneUserGroup(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return UserGroupModel 用户组关联领域对象
     */
    public UserGroupModel getOneUserGroupModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param userGroupQuery 用户组关联查询参数对象
     * @return UserGroupModel 用户组关联领域对象
     */
    public UserGroupModel getOneUserGroupModel(UserGroupQuery userGroupQuery);

    /**
     * 查询用户组
     * @param userGroupQuery
     * @return
     */
    public Page<GroupModel> getUserGroupsPage(UserGroupQuery userGroupQuery);

    /**
     * 查询用户组
     * @param userGroupQuery
     * @return
     */
    public List<GroupModel> getUserGroups(UserGroupQuery userGroupQuery);


    /**
     * 批量删除
     * @param userId
     * @param groupIds
     */
    public void deleteUserGroups(String userId, String[] groupIds);

    /**
     * 查询未绑定用户组用户
     * @param groupId
     * @return
     */
    public List<UserModel> getUnbindUsers(String groupId);
}
