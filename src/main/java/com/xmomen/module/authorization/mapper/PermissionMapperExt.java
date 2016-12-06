package com.xmomen.module.authorization.mapper;

import com.xmomen.module.authorization.entity.Permission;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
public interface PermissionMapperExt {

    String PermissionMapperNameSpace = "com.xmomen.module.authorization.mapper.PermissionMapperExt.";

    /**
     * 查询用户所有权限
     * @param username
     * @return
     */
    @Select("select p.* from sys_permissions p left join `sys_roles_permissions` rp on rp.`PERMISSION_ID`=p.`ID` left join sys_roles r on rp.`ROLE_ID`=r.`ID` left join sys_users_roles ur on ur.role_id = r.id left join sys_users u on u.id=ur.user_id where u.username = #{username}")
    @ResultType(Permission.class)
    List<Permission> getPermissionList(String username);
}
