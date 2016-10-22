package com.xmomen.module.authorization.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.authorization.model.UserPermissionCreate;
import com.xmomen.module.authorization.model.UserPermissionQuery;
import com.xmomen.module.authorization.model.UserPermissionUpdate;
import com.xmomen.module.authorization.model.UserPermissionModel;
import com.xmomen.module.authorization.entity.UserPermission;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-20 23:14:13
 * @version 1.0.0
 */
public interface UserPermissionService {

    /**
     * 新增用户权限
     * @param  userPermissionCreate   新增用户权限对象参数
     * @return  UserPermissionModel    用户权限领域对象
     */
    UserPermissionModel createUserPermission(UserPermissionCreate userPermissionCreate);

    /**
     * 新增用户权限实体对象
     * @param   userPermission 新增用户权限实体对象参数
     * @return  UserPermission 用户权限实体对象
     */
    UserPermission createUserPermission(UserPermission userPermission);

    /**
     * 更新用户权限
     * @param userPermissionUpdate    更新用户权限对象参数
     */
    void updateUserPermission(UserPermissionUpdate userPermissionUpdate);

    /**
     * 更新用户权限实体对象
     * @param   userPermission 新增用户权限实体对象参数
     * @return  UserPermission 用户权限实体对象
     */
    void updateUserPermission(UserPermission userPermission);

    /**
     * 批量删除用户权限
     * @param ids   主键数组
     */
    void deleteUserPermission(String[] ids);

    /**
     * 删除用户权限
     * @param id   主键
     */
    void deleteUserPermission(String id);

    /**
     * 查询用户权限领域分页对象（带参数条件）
     * @param userPermissionQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<UserPermissionModel>   用户权限参数对象
     */
    Page<UserPermissionModel> getUserPermissionModelPage(int limit, int offset, UserPermissionQuery userPermissionQuery);

    /**
     * 查询用户权限领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<UserPermissionModel> 用户权限领域对象
     */
    Page<UserPermissionModel> getUserPermissionModelPage(int limit, int offset);

    /**
     * 查询用户权限领域集合对象（带参数条件）
     * @param userPermissionQuery 查询参数对象
     * @return List<UserPermissionModel> 用户权限领域集合对象
     */
    List<UserPermissionModel> getUserPermissionModelList(UserPermissionQuery userPermissionQuery);

    /**
     * 查询用户权限领域集合对象（无参数条件）
     * @return List<UserPermissionModel> 用户权限领域集合对象
     */
    List<UserPermissionModel> getUserPermissionModelList();

    /**
     * 查询用户权限实体对象
     * @param id 主键
     * @return UserPermission 用户权限实体对象
     */
    UserPermission getOneUserPermission(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return UserPermissionModel 用户权限领域对象
     */
    UserPermissionModel getOneUserPermissionModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param userPermissionQuery 用户权限查询参数对象
     * @return UserPermissionModel 用户权限领域对象
     */
    UserPermissionModel getOneUserPermissionModel(UserPermissionQuery userPermissionQuery) throws TooManyResultsException;
}
