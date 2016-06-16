package com.xmomen.module.permission.mapper;

import com.xmomen.module.permission.entity.SysPermissions;
import com.xmomen.module.permission.entity.SysRoles;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * Created by Jeng on 2016/1/22.
 */
public interface PermissionMapper {

    public static final String PermissionMapperNameSpace = "com.xmomen.module.permission.mapper.PermissionMapper.";

    /**
     * 查询角色集合
     * @param username 用户名
     * @return 角色集合对象
     */
    @Select("select r.* from sys_roles r left join sys_users_roles ur on ur.role_id = r.id left join sys_users u on u.id=ur.user_id where u.username = #{username}")
    @ResultType(SysRoles.class)
    public List<SysRoles> getRoleList(String username);

    /**
     * 查询用户所有权限
     * @param username 用户名
     * @return 权限集合对象
     */
    @Select("select p.* from sys_permissions p left join `sys_roles_permissions` rp on rp.`PERMISSION_ID`=p.`ID` left join sys_roles r on rp.`ROLE_ID`=r.`ID` left join sys_users_roles ur on ur.role_id = r.id left join sys_users u on u.id=ur.user_id where u.username = #{username} group by permission_code")
    @ResultType(SysPermissions.class)
    public List<SysPermissions> getPermissionList(String username);

    /**
     * 修改密码
     * @param username 用户名
     * @param currentPassword 当前密码
     * @param password 新密码
     */
    @Update("UPDATE sys_users SET PASSWORD = #{password},SALT=#{salt} WHERE username = #{username} AND PASSWORD=#{currentPassword}")
    public void resetPassword(@Param(value = "username") String username,
                              @Param(value = "currentPassword") String currentPassword,
                              @Param(value = "password") String password,
                              @Param(value = "salt") String salt);
}
