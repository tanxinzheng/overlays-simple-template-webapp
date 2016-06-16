package com.xmomen.module.permission.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.permission.entity.SysRoles;
import com.xmomen.module.permission.entity.SysRolesExample;
import org.apache.ibatis.annotations.Param;

public interface SysRolesMapper extends MybatisMapper {
    int countByExample(SysRolesExample example);

    int deleteByExample(SysRolesExample example);

    int insertSelective(SysRoles record);

    int updateByExampleSelective(@Param("record") SysRoles record, @Param("example") SysRolesExample example);
}