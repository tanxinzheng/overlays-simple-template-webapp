package com.xmomen.module.permission.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.permission.entity.SysUsersRoles;
import com.xmomen.module.permission.entity.SysUsersRolesExample;
import org.apache.ibatis.annotations.Param;

public interface SysUsersRolesMapper extends MybatisMapper {
    int countByExample(SysUsersRolesExample example);

    int deleteByExample(SysUsersRolesExample example);

    int insertSelective(SysUsersRoles record);

    int updateByExampleSelective(@Param("record") SysUsersRoles record, @Param("example") SysUsersRolesExample example);
}