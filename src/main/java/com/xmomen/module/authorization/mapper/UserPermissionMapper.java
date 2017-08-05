package com.xmomen.module.authorization.mapper;

import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.UserPermissionQuery;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public interface UserPermissionMapper {


    /**
     * 查询用户权限
     * @param userPermissionQuery
     * @return
     */
    List<PermissionModel> getUserPermissions(UserPermissionQuery userPermissionQuery);

}
