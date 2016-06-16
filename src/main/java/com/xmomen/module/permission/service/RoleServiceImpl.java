package com.xmomen.module.permission.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.permission.entity.*;
import com.xmomen.module.permission.mapper.PermissionMapper;
import com.xmomen.module.permission.model.GroupPermissionRelation;
import com.xmomen.module.permission.model.UserGroupRelation;
import com.xmomen.module.user.mapper.UserMapper;
import com.xmomen.module.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Permission;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private MybatisDao mybatisDao;

	@Autowired
	UserService userService;

	/**
	 * 根据角色ID查询用户
	 *
	 * @param roleId
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@Override
	public Page<UserGroupRelation> findUsersByRoles(String roleId, boolean chose, Integer pageSize, Integer pageNum) {
		Map map = new HashMap();
		map.put("id" , roleId);
		map.put("chose" , chose);
		return (Page<UserGroupRelation>) mybatisDao.selectPage(PermissionMapper.PermissionMapperNameSpace + "findUsersByRolesId", map, pageSize, pageNum);
	}

	@Override
	public Page<SysRoles> findRoles(String keyValue, Integer pageSize, Integer pageNum) {
		SysRolesExample sysRolesExample = new SysRolesExample();
		if(StringUtils.isNotBlank(keyValue)){
			sysRolesExample.createCriteria()
					.andRoleLike(keyValue);
			sysRolesExample.or().andDescriptionLike(keyValue);
		}
		return mybatisDao.selectPageByExample(sysRolesExample, pageSize, pageNum);
	}

	/**
	 * 获取角色
	 *
	 * @param roleId
	 */
	@Override
	public SysRoles getRole(Integer roleId) {
		return mybatisDao.selectByPrimaryKey(SysRoles.class, roleId);
	}

	/**
	 * 更新角色
	 *
	 * @param sysRoles
	 */
	@Override
	public void updateRole(SysRoles sysRoles) {
		mybatisDao.update(sysRoles);
	}

	/**
	 * 更新用户组信息并添加用户
	 *
	 * @param sysRoles
	 * @param userIdList
	 */
	@Override
	@Transactional
	public void updateRole(SysRoles sysRoles, List<Integer> userIdList) {
		mybatisDao.update(sysRoles);
		for (Integer userId : userIdList) {
			correlationRoles(userId, sysRoles.getId());
		}
	}


	public SysRoles createRole(SysRoles role) {
    	role = mybatisDao.saveByModel(role);
        return role;
    }

    public void deleteRole(Integer roleId) {
    	mybatisDao.deleteByPrimaryKey(SysRoles.class, roleId);
    }

	@Override
	public Page<GroupPermissionRelation> findPermissionByRoles(String roleId, boolean chose, Integer pageSize, Integer pageNum) {
		Map map = new HashMap();
		map.put("id" , roleId);
		map.put("chose" , chose);
		return (Page<GroupPermissionRelation>) mybatisDao.selectPage(PermissionMapper.PermissionMapperNameSpace + "findPermissionByRole", map, pageSize, pageNum);
	}

	/**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Integer roleId, Integer... permissionIds) {
    	for(Integer permissionId : permissionIds){
    		SysRolesPermissions sysRolesPermissionsKey = new SysRolesPermissions();
    		sysRolesPermissionsKey.setPermissionId(permissionId);
    		sysRolesPermissionsKey.setRoleId(roleId);
    		mybatisDao.insert(sysRolesPermissionsKey);
    	}
    }

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void unCorrelationPermissions(Integer roleId, Integer... permissionIds) {
		SysRolesPermissionsExample sysRolesPermissionsExample = new SysRolesPermissionsExample();
		sysRolesPermissionsExample.createCriteria()
				.andRoleIdEqualTo(roleId)
				.andPermissionIdIn(Arrays.asList(permissionIds));
		mybatisDao.deleteByExample(sysRolesPermissionsExample);
    }


	/**
	 * 添加用户-角色关系
	 * @param userId
	 * @param roleIds
	 */
	@Transactional
	public void correlationRoles(Integer userId, Integer... roleIds) {
		for (int i = 0; i < roleIds.length; i++) {
			Integer roleId = roleIds[i];
			SysUsersRoles sysUsersRoles = new SysUsersRoles();
			sysUsersRoles.setUserId(userId);
			sysUsersRoles.setRoleId(roleId);
			mybatisDao.insert(sysUsersRoles);
		}
	}


	/**
	 * 移除用户-角色关系
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationRoles(Integer userId, Integer... roleIds) {
		SysUsersRolesExample sysUsersRolesExample = new SysUsersRolesExample();
		sysUsersRolesExample.createCriteria()
				.andUserIdEqualTo(userId)
				.andRoleIdIn(Arrays.asList(roleIds));
		mybatisDao.deleteByExample(sysUsersRolesExample);
	}


}
