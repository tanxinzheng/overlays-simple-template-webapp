package com.xmomen.module.permission.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.permission.entity.SysPermissions;
import com.xmomen.module.permission.entity.SysPermissionsExample;
import org.apache.ibatis.annotations.Param;

public interface SysPermissionsMapper extends MybatisMapper {
    int countByExample(SysPermissionsExample example);

    int deleteByExample(SysPermissionsExample example);

    int insertSelective(SysPermissions record);

    int updateByExampleSelective(@Param("record") SysPermissions record, @Param("example") SysPermissionsExample example);
}