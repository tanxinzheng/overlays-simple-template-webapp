package com.xmomen.module.authorization.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.UserPermissionQuery;

import java.util.List;

/**
 * Created by tanxinzheng on 17/8/3.
 */
public interface UserPermissionService {

    /**
     * 查询用户权限
     * @param userPermissionQuery
     * @return
     */
    public List<PermissionModel> getUserPermissions(UserPermissionQuery userPermissionQuery);
}
