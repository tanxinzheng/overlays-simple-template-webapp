package com.xmomen.module.authorization.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.authorization.entity.GroupPermission;
import com.xmomen.module.authorization.model.GroupPermissionCreate;
import com.xmomen.module.authorization.model.GroupPermissionModel;
import com.xmomen.module.authorization.model.GroupPermissionQuery;
import com.xmomen.module.authorization.model.GroupPermissionUpdate;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
public interface GroupPermissionService {

    /**
     * 新增组权限
     * @param  groupPermissionCreate   新增组权限对象参数
     * @return  GroupPermissionModel    组权限领域对象
     */
    GroupPermissionModel createGroupPermission(GroupPermissionCreate groupPermissionCreate);

    /**
     * 新增组权限实体对象
     * @param   groupPermission 新增组权限实体对象参数
     * @return  GroupPermission 组权限实体对象
     */
    GroupPermission createGroupPermission(GroupPermission groupPermission);

    /**
     * 批量新增组权限实体对象
     * @param groupId   组主键
     * @param permissionIds 权限主键集
     * @return List<GroupPermission> 组权限实体对象集
     */
    List<GroupPermission> createGroupPermissions(String groupId, String[] permissionIds);

    /**
     * 更新组权限
     * @param groupPermissionUpdate    更新组权限对象参数
     */
    void updateGroupPermission(GroupPermissionUpdate groupPermissionUpdate);

    /**
     * 更新组权限实体对象
     * @param   groupPermission 新增组权限实体对象参数
     * @return  GroupPermission 组权限实体对象
     */
    void updateGroupPermission(GroupPermission groupPermission);

    /**
     * 批量删除组权限
     * @param ids   主键数组
     */
    void deleteGroupPermission(String[] ids);

    /**
     * 删除组权限
     * @param id   主键
     */
    void deleteGroupPermission(String id);

    /**
     * 查询组权限领域分页对象（带参数条件）
     * @param groupPermissionQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<GroupPermissionModel>   组权限参数对象
     */
    Page<GroupPermissionModel> getGroupPermissionModelPage(int limit, int offset, GroupPermissionQuery groupPermissionQuery);

    /**
     * 查询组权限领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<GroupPermissionModel> 组权限领域对象
     */
    Page<GroupPermissionModel> getGroupPermissionModelPage(int limit, int offset);

    /**
     * 查询组权限领域集合对象（带参数条件）
     * @param groupPermissionQuery 查询参数对象
     * @return List<GroupPermissionModel> 组权限领域集合对象
     */
    List<GroupPermissionModel> getGroupPermissionModelList(GroupPermissionQuery groupPermissionQuery);

    /**
     * 查询组权限领域集合对象（无参数条件）
     * @return List<GroupPermissionModel> 组权限领域集合对象
     */
    List<GroupPermissionModel> getGroupPermissionModelList();

    /**
     * 查询组权限实体对象
     * @param id 主键
     * @return GroupPermission 组权限实体对象
     */
    GroupPermission getOneGroupPermission(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return GroupPermissionModel 组权限领域对象
     */
    GroupPermissionModel getOneGroupPermissionModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param groupPermissionQuery 组权限查询参数对象
     * @return GroupPermissionModel 组权限领域对象
     */
    GroupPermissionModel getOneGroupPermissionModel(GroupPermissionQuery groupPermissionQuery) throws TooManyResultsException;
}
