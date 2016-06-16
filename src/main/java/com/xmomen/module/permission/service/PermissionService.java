package com.xmomen.module.permission.service;


import com.xmomen.module.permission.entity.SysPermissions;
import com.xmomen.module.permission.model.UpdatePermission;

import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface PermissionService {
    /**
     * 创建权限资源
     * @param permission
     * @return
     */
    public SysPermissions createPermission(SysPermissions permission);

    /**
     * 删除权限资源
     * @param permissionId
     */
    public void deletePermission(Integer permissionId);

    /**
     * 更新权限资源
     * @param updatePermission
     */
    public void updatePermission(UpdatePermission updatePermission);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
}
