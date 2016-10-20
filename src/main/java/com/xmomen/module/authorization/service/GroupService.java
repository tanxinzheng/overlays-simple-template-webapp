package com.xmomen.module.authorization.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.authorization.model.GroupCreate;
import com.xmomen.module.authorization.model.GroupQuery;
import com.xmomen.module.authorization.model.GroupUpdate;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.entity.Group;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:46:57
 * @version 1.0.0
 */
public interface GroupService {

    /**
     * 新增组
     * @param  groupCreate   新增组对象参数
     * @return  GroupModel    组领域对象
     */
    GroupModel createGroup(GroupCreate groupCreate);

    /**
     * 新增组实体对象
     * @param   group 新增组实体对象参数
     * @return  Group 组实体对象
     */
    Group createGroup(Group group);

    /**
     * 更新组
     * @param groupUpdate    更新组对象参数
     */
    void updateGroup(GroupUpdate groupUpdate);

    /**
     * 更新组实体对象
     * @param   group 新增组实体对象参数
     * @return  Group 组实体对象
     */
    void updateGroup(Group group);

    /**
     * 批量删除组
     * @param ids   主键数组
     */
    void deleteGroup(String[] ids);

    /**
     * 删除组
     * @param id   主键
     */
    void deleteGroup(String id);

    /**
     * 查询组领域分页对象（带参数条件）
     * @param groupQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<GroupModel>   组参数对象
     */
    Page<GroupModel> getGroupModelPage(int limit, int offset, GroupQuery groupQuery);

    /**
     * 查询组领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<GroupModel> 组领域对象
     */
    Page<GroupModel> getGroupModelPage(int limit, int offset);

    /**
     * 查询组领域集合对象（带参数条件）
     * @param groupQuery 查询参数对象
     * @return List<GroupModel> 组领域集合对象
     */
    List<GroupModel> getGroupModelList(GroupQuery groupQuery);

    /**
     * 查询组领域集合对象（无参数条件）
     * @return List<GroupModel> 组领域集合对象
     */
    List<GroupModel> getGroupModelList();

    /**
     * 查询组实体对象
     * @param id 主键
     * @return Group 组实体对象
     */
    Group getOneGroup(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return GroupModel 组领域对象
     */
    GroupModel getOneGroupModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param groupQuery 组查询参数对象
     * @return GroupModel 组领域对象
     */
    GroupModel getOneGroupModel(GroupQuery groupQuery) throws TooManyResultsException;
}
