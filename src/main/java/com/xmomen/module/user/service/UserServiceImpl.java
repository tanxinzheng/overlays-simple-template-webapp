package com.xmomen.module.user.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.permission.entity.SysUsersRoles;
import com.xmomen.module.permission.entity.SysUsersRolesExample;
import com.xmomen.module.permission.service.RoleService;
import com.xmomen.module.user.entity.*;
import com.xmomen.module.user.mapper.UserMapper;
import com.xmomen.module.user.model.CreateUser;
import com.xmomen.module.user.model.UpdateUser;
import com.xmomen.module.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {


    private PasswordHelper passwordHelper;

    public void setPasswordHelper(PasswordHelper passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

    @Autowired
    MybatisDao mybatisDao;

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired
    RoleService roleService;

    /**
     * 创建用户
     * @param user
     */
    @Transactional
    public SysUsers createUser(CreateUser user) {
        //加密密码
        String salt = passwordHelper.getSalt();
        String newPassword = passwordHelper.encryptPassword(user.getPassword(), salt);
        SysUsers sysUsers = new SysUsers();
        sysUsers.setSalt(UUID.randomUUID().toString().toUpperCase());
        sysUsers.setUsername(user.getUsername());
        sysUsers.setEmail(user.getEmail());
        sysUsers.setRealname(user.getRealname());
        sysUsers.setAge(user.getAge());
        sysUsers.setOfficeTel(user.getOfficeTel());
        sysUsers.setPhoneNumber(user.getPhoneNumber());
        sysUsers.setQq(user.getQq());
        sysUsers.setSex(user.getSex());
        sysUsers.setSalt(salt);
        sysUsers.setPassword(newPassword);
        sysUsers.setLocked(user.getLocked() ? 1 : 0);
        sysUsers = mybatisDao.saveByModel(sysUsers);
        roleService.correlationRoles(sysUsers.getId(), user.getUserGroupIds());
        return sysUsers;
    }

    /**
     * 更新用户
     *
     * @param updateUser 用户资源更新信息
     */
    @Transactional
    @Override
    public void updateUser(UpdateUser updateUser) {
        SysUsers sysUsers = new SysUsers();
        sysUsers.setId(updateUser.getId());
        sysUsers.setUsername(updateUser.getUsername());
        sysUsers.setEmail(updateUser.getEmail());
        sysUsers.setLocked(updateUser.getLocked() ? 1 : 0);
        sysUsers.setAge(updateUser.getAge());
        sysUsers.setOfficeTel(updateUser.getOfficeTel());
        sysUsers.setPhoneNumber(updateUser.getPhoneNumber());
        sysUsers.setSex(updateUser.getSex());
        sysUsers.setQq(updateUser.getQq());
        sysUsers.setRealname(updateUser.getRealname());
        //更新权限
        SysUsersRolesExample sysUsersRolesExample = new SysUsersRolesExample();
        sysUsersRolesExample.createCriteria().andUserIdEqualTo(sysUsers.getId());
		mybatisDao.deleteByExample(sysUsersRolesExample);
        roleService.correlationRoles(sysUsers.getId(), updateUser.getUserGroupIds());
        mybatisDao.save(sysUsers);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    @Transactional
    public void changePassword(Integer userId, String newPassword) {
        SysUsers user = mybatisDao.selectByPrimaryKey(SysUsers.class, userId);
        String salt = passwordHelper.getSalt();
        user.setPassword(newPassword);
        user.setSalt(salt);
        passwordHelper.encryptPassword(user.getPassword(), salt);
        mybatisDao.update(user);
    }

    @Override
    public void changePassword(String username, String currentPassword, String newPassword) {
        SysUsers sysUsers = new SysUsers();
        sysUsers.setUsername(username);
        sysUsers = mybatisDao.selectOneByModel(sysUsers);
        String currentRealPwd = passwordHelper.encryptPassword(currentPassword, sysUsers.getSalt());
        if(sysUsers == null || !sysUsers.getPassword().equals(currentRealPwd)){
            throw new IllegalArgumentException("旧密码不正确");
        }
        String newSalt = passwordHelper.getSalt();
        String newCurrentRealPwd = passwordHelper.encryptPassword(newPassword, newSalt);
        userMapper.resetPassword(username, currentRealPwd, newCurrentRealPwd, newSalt);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public SysUsers findByUsername(String username) {
        SysUsersExample sysUsersExample = new SysUsersExample();
        sysUsersExample.createCriteria().andUsernameEqualTo(username);
        sysUsersExample.or().andEmailEqualTo(username);
        sysUsersExample.or().andPhoneNumberEqualTo(username);
        return mybatisDao.selectOneByExample(sysUsersExample);
    }



}
