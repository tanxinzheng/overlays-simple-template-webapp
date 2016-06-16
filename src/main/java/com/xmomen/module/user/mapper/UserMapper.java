package com.xmomen.module.user.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * Created by Jeng on 2016/1/22.
 */
public interface UserMapper {

    public static final String UserMapperNameSpace = "com.xmomen.module.user.mapper.UserMapper.";

    /**
     * 修改密码
     * @param username
     * @param currentPassword
     * @param password
     */
    @Update("UPDATE sys_users SET PASSWORD = #{password},SALT=#{salt} WHERE username = #{username} AND PASSWORD=#{currentPassword}")
    public void resetPassword(@Param(value = "username") String username,
                              @Param(value = "currentPassword") String currentPassword,
                              @Param(value = "password") String password,
                              @Param(value = "salt") String salt);
}
