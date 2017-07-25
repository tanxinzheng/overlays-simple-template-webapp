package com.xmomen.module.authorization.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.authorization.model.GroupQuery;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.model.Group;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
public interface GroupService {

    /**
     * 新增用户组
     * @param  groupModel   新增用户组对象参数
     * @return  GroupModel    用户组领域对象
     */
    public GroupModel createGroup(GroupModel groupModel);

    /**
     * 新增用户组实体对象
     * @param   group 新增用户组实体对象参数
     * @return  Group 用户组实体对象
     */
    public Group createGroup(Group group);

    /**
    * 批量新增用户组
    * @param groupModels     新增用户组对象集合参数
    * @return List<GroupModel>    用户组领域对象集合
    */
    List<GroupModel> createGroups(List<GroupModel> groupModels);

    /**
    * 更新用户组
    *
    * @param groupModel 更新用户组对象参数
    * @param groupQuery 过滤用户组对象参数
    */
    public void updateGroup(GroupModel groupModel, GroupQuery groupQuery);

    /**
     * 更新用户组
     * @param groupModel    更新用户组对象参数
     */
    public void updateGroup(GroupModel groupModel);

    /**
     * 更新用户组实体对象
     * @param   group 新增用户组实体对象参数
     * @return  Group 用户组实体对象
     */
    public void updateGroup(Group group);

    /**
     * 批量删除用户组
     * @param ids   主键数组
     */
    public void deleteGroup(String[] ids);

    /**
     * 删除用户组
     * @param id   主键
     */
    public void deleteGroup(String id);

    /**
     * 查询用户组领域分页对象（带参数条件）
     * @param groupQuery 查询参数
     * @return Page<GroupModel>   用户组参数对象
     */
    public Page<GroupModel> getGroupModelPage(GroupQuery groupQuery);

    /**
     * 查询用户组领域集合对象（带参数条件）
     * @param groupQuery 查询参数对象
     * @return List<GroupModel> 用户组领域集合对象
     */
    public List<GroupModel> getGroupModelList(GroupQuery groupQuery);

    /**
     * 查询用户组
     * @param groupQuery
     * @return
     */
    public Page<GroupModel> getUserGroups(GroupQuery groupQuery);

    /**
     * 查询用户组实体对象
     * @param id 主键
     * @return Group 用户组实体对象
     */
    public Group getOneGroup(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return GroupModel 用户组领域对象
     */
    public GroupModel getOneGroupModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param groupQuery 用户组查询参数对象
     * @return GroupModel 用户组领域对象
     */
    public GroupModel getOneGroupModel(GroupQuery groupQuery) throws TooManyResultsException;
}
