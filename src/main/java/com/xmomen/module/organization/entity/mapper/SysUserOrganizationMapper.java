package com.xmomen.module.organization.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.organization.entity.SysUserOrganization;
import com.xmomen.module.organization.entity.SysUserOrganizationExample;
import org.apache.ibatis.annotations.Param;

public interface SysUserOrganizationMapper extends MybatisMapper {
    int countByExample(SysUserOrganizationExample example);

    int deleteByExample(SysUserOrganizationExample example);

    int insertSelective(SysUserOrganization record);

    int updateByExampleSelective(@Param("record") SysUserOrganization record, @Param("example") SysUserOrganizationExample example);
}