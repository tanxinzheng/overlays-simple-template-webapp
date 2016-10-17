package com.xmomen.module.authorization.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.authorization.entity.Permission;
import com.xmomen.module.authorization.entity.PermissionExample;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper extends MybatisMapper {
    int countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int insertSelective(Permission record);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);
}