package com.xmomen.module.user.service;

import com.xmomen.module.user.entity.SysUsers;
import com.xmomen.module.user.model.CreateUser;
import com.xmomen.module.user.model.UpdateUser;

import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    public SysUsers createUser(CreateUser user);

    /**
     * 更新用户
     * @param updateUserVo
     */
    public void updateUser(UpdateUser updateUserVo);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Integer userId, String newPassword);

    /**
     * 修改密码
     * @param currentPassword
     * @param newPassword
     */
    public void changePassword(String username, String currentPassword, String newPassword);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public SysUsers findByUsername(String username);

}
