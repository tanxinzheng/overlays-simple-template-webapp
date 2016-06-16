package com.xmomen.module.organization.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.organization.entity.SysOrganization;
import com.xmomen.module.organization.entity.SysOrganizationExample;
import org.apache.ibatis.annotations.Param;

public interface SysOrganizationMapper extends MybatisMapper {
    int countByExample(SysOrganizationExample example);

    int deleteByExample(SysOrganizationExample example);

    int insertSelective(SysOrganization record);

    int updateByExampleSelective(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);
}