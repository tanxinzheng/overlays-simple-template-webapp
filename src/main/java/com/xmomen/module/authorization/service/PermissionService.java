package com.xmomen.module.authorization.service;

import com.github.pagehelper.Page;
import com.xmomen.module.authorization.model.PermissionQuery;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.Permission;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public interface PermissionService {

    /**
     * 新增权限
     * @param  permissionModel   新增权限对象参数
     * @return  PermissionModel    权限领域对象
     */
    public PermissionModel createPermission(PermissionModel permissionModel);

    /**
     * 新增权限实体对象
     * @param   permission 新增权限实体对象参数
     * @return  Permission 权限实体对象
     */
    public Permission createPermission(Permission permission);

    /**
    * 批量新增权限
    * @param permissionModels     新增权限对象集合参数
    * @return List<PermissionModel>    权限领域对象集合
    */
    List<PermissionModel> createPermissions(List<PermissionModel> permissionModels, String createdUserId);

    /**
    * 更新权限
    *
    * @param permissionModel 更新权限对象参数
    * @param permissionQuery 过滤权限对象参数
    */
    public void updatePermission(PermissionModel permissionModel, PermissionQuery permissionQuery);

    /**
     * 更新权限
     * @param permissionModel    更新权限对象参数
     */
    public void updatePermission(PermissionModel permissionModel);

    /**
     * 更新权限实体对象
     * @param   permission 新增权限实体对象参数
     * @return  Permission 权限实体对象
     */
    public void updatePermission(Permission permission);

    /**
     * 批量删除权限
     * @param ids   主键数组
     */
    public void deletePermission(String[] ids);

    /**
     * 删除权限
     * @param id   主键
     */
    public void deletePermission(String id);

    /**
     * 查询权限领域分页对象（带参数条件）
     * @param permissionQuery 查询参数
     * @return Page<PermissionModel>   权限参数对象
     */
    public Page<PermissionModel> getPermissionModelPage(PermissionQuery permissionQuery);

    /**
     * 查询权限领域集合对象（带参数条件）
     * @param permissionQuery 查询参数对象
     * @return List<PermissionModel> 权限领域集合对象
     */
    public List<PermissionModel> getPermissionModelList(PermissionQuery permissionQuery);

    /**
     * 查询权限实体对象
     * @param id 主键
     * @return Permission 权限实体对象
     */
    public Permission getOnePermission(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return PermissionModel 权限领域对象
     */
    public PermissionModel getOnePermissionModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param permissionQuery 权限查询参数对象
     * @return PermissionModel 权限领域对象
     */
    public PermissionModel getOnePermissionModel(PermissionQuery permissionQuery) throws TooManyResultsException;
}
