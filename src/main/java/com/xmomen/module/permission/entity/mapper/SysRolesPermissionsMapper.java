package com.xmomen.module.permission.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.permission.entity.SysRolesPermissions;
import com.xmomen.module.permission.entity.SysRolesPermissionsExample;
import org.apache.ibatis.annotations.Param;

public interface SysRolesPermissionsMapper extends MybatisMapper {
    int countByExample(SysRolesPermissionsExample example);

    int deleteByExample(SysRolesPermissionsExample example);

    int insertSelective(SysRolesPermissions record);

    int updateByExampleSelective(@Param("record") SysRolesPermissions record, @Param("example") SysRolesPermissionsExample example);
}