package com.xmomen.module.authorization.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.authorization.model.GroupPermissionQuery;
import com.xmomen.module.authorization.model.GroupPermissionModel;
import com.xmomen.module.authorization.model.GroupPermission;
import com.xmomen.module.authorization.model.PermissionModel;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public interface GroupPermissionService {

    /**
     * 新增组权限
     * @param  groupPermissionModel   新增组权限对象参数
     * @return  GroupPermissionModel    组权限领域对象
     */
    public GroupPermissionModel createGroupPermission(GroupPermissionModel groupPermissionModel);

    /**
     * 新增组权限实体对象
     * @param   groupPermission 新增组权限实体对象参数
     * @return  GroupPermission 组权限实体对象
     */
    public GroupPermission createGroupPermission(GroupPermission groupPermission);

    /**
     * 批量新增用户组所属权限
     * @param groupId
     * @param permissionIds
     * @return
     */
    public void createGroupPermissions(String groupId, String... permissionIds);

    /**
    * 更新组权限
    *
    * @param groupPermissionModel 更新组权限对象参数
    * @param groupPermissionQuery 过滤组权限对象参数
    */
    public void updateGroupPermission(GroupPermissionModel groupPermissionModel, GroupPermissionQuery groupPermissionQuery);

    /**
     * 更新组权限
     * @param groupPermissionModel    更新组权限对象参数
     */
    public void updateGroupPermission(GroupPermissionModel groupPermissionModel);

    /**
     * 更新组权限实体对象
     * @param   groupPermission 新增组权限实体对象参数
     * @return  GroupPermission 组权限实体对象
     */
    public void updateGroupPermission(GroupPermission groupPermission);

    /**
     * 批量删除组权限
     * @param ids   主键数组
     */
    public void deleteGroupPermission(String[] ids);

    /**
     * 删除组权限
     * @param id   主键
     */
    public void deleteGroupPermission(String id);

    /**
     * 查询组权限领域分页对象（带参数条件）
     * @param groupPermissionQuery 查询参数
     * @return Page<GroupPermissionModel>   组权限参数对象
     */
    public Page<GroupPermissionModel> getGroupPermissionModelPage(GroupPermissionQuery groupPermissionQuery);

    /**
     * 查询组权限领域集合对象（带参数条件）
     * @param groupPermissionQuery 查询参数对象
     * @return List<GroupPermissionModel> 组权限领域集合对象
     */
    public List<GroupPermissionModel> getGroupPermissionModelList(GroupPermissionQuery groupPermissionQuery);

    /**
     * 查询组权限实体对象
     * @param id 主键
     * @return GroupPermission 组权限实体对象
     */
    public GroupPermission getOneGroupPermission(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return GroupPermissionModel 组权限领域对象
     */
    public GroupPermissionModel getOneGroupPermissionModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param groupPermissionQuery 组权限查询参数对象
     * @return GroupPermissionModel 组权限领域对象
     */
    public GroupPermissionModel getOneGroupPermissionModel(GroupPermissionQuery groupPermissionQuery) throws TooManyResultsException;

    /**
     * 查询用户组权限
     * @param groupPermissionQuery
     * @return
     */
    public Page<PermissionModel> getGroupPermissions(GroupPermissionQuery groupPermissionQuery);
}
