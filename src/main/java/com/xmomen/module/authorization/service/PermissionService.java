package com.xmomen.module.authorization.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.authorization.entity.Permission;
import com.xmomen.module.authorization.model.PermissionCreate;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.PermissionQuery;
import com.xmomen.module.authorization.model.PermissionUpdate;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
public interface PermissionService {

    /**
     * 新增权限
     * @param  permissionCreate   新增权限对象参数
     * @return  PermissionModel    权限领域对象
     */
    PermissionModel createPermission(PermissionCreate permissionCreate);

    /**
     * 新增权限实体对象
     * @param   permission 新增权限实体对象参数
     * @return  Permission 权限实体对象
     */
    Permission createPermission(Permission permission);

    /**
     * 更新权限
     * @param permissionUpdate    更新权限对象参数
     */
    void updatePermission(PermissionUpdate permissionUpdate);

    /**
     * 更新权限实体对象
     * @param   permission 新增权限实体对象参数
     * @return  Permission 权限实体对象
     */
    void updatePermission(Permission permission);

    /**
     * 批量删除权限
     * @param ids   主键数组
     */
    void deletePermission(String[] ids);

    /**
     * 删除权限
     * @param id   主键
     */
    void deletePermission(String id);

    /**
     * 查询权限领域分页对象（带参数条件）
     * @param permissionQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<PermissionModel>   权限参数对象
     */
    Page<PermissionModel> getPermissionModelPage(int limit, int offset, PermissionQuery permissionQuery);

    /**
     * 查询权限领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<PermissionModel> 权限领域对象
     */
    Page<PermissionModel> getPermissionModelPage(int limit, int offset);

    /**
     * 查询权限领域集合对象（带参数条件）
     * @param permissionQuery 查询参数对象
     * @return List<PermissionModel> 权限领域集合对象
     */
    List<PermissionModel> getPermissionModelList(PermissionQuery permissionQuery);

    /**
     * 查询权限领域集合对象（无参数条件）
     * @return List<PermissionModel> 权限领域集合对象
     */
    List<PermissionModel> getPermissionModelList();

    /**
     * 查询权限实体对象
     * @param id 主键
     * @return Permission 权限实体对象
     */
    Permission getOnePermission(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return PermissionModel 权限领域对象
     */
    PermissionModel getOnePermissionModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param permissionQuery 权限查询参数对象
     * @return PermissionModel 权限领域对象
     */
    PermissionModel getOnePermissionModel(PermissionQuery permissionQuery) throws TooManyResultsException;
}
