package com.xmomen.module.authorization.service.impl;

import com.xmomen.module.authorization.mapper.UserPermissionMapper;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.UserPermissionQuery;
import com.xmomen.module.authorization.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tanxinzheng on 17/8/3.
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    UserPermissionMapper userPermissionMapper;

    /**
     * 查询用户权限
     *
     * @param userPermissionQuery
     * @return
     */
    @Override
    public List<PermissionModel> getUserPermissions(UserPermissionQuery userPermissionQuery) {
        if(userPermissionQuery == null){
            return null;
        }
        return userPermissionMapper.getUserPermissions(userPermissionQuery);
    }
}
