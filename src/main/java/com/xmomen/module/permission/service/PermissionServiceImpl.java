package com.xmomen.module.permission.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.permission.entity.SysPermissions;
import com.xmomen.module.permission.entity.SysRoles;
import com.xmomen.module.permission.mapper.PermissionMapper;
import com.xmomen.module.permission.model.UpdatePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private MybatisDao mybatisDao;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    @Transactional
    public SysPermissions createPermission(SysPermissions permission) {
    	permission = mybatisDao.saveByModel(permission);
        return permission;
    }

    @Override
    @Transactional
    public void deletePermission(Integer permissionId) {
    	mybatisDao.deleteByPrimaryKey(SysPermissions.class, permissionId);
    }

    @Override
    public void updatePermission(UpdatePermission updatePermission) {
        SysPermissions sysPermissions = new SysPermissions();
        sysPermissions.setAvailable(updatePermission.getAvailable() ? 1 : 0);
        sysPermissions.setDescription(updatePermission.getDescription());
        sysPermissions.setId(updatePermission.getId());
        mybatisDao.update(sysPermissions);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        List<SysRoles> sysRolesList = permissionMapper.getRoleList(username);
        Set<String> roles = new HashSet();
        for (int i = 0; i < sysRolesList.size(); i++) {
            roles.add(sysRolesList.get(i).getRole());
        }
        return roles;
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        List<SysPermissions> sysPermissionsList = permissionMapper.getPermissionList(username);
        Set<String> permissions = new HashSet();
        for (int i = 0; i < sysPermissionsList.size(); i++) {
            permissions.add(sysPermissionsList.get(i).getPermissionCode());
        }
        return permissions;
    }
}
