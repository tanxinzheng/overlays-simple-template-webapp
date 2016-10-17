package com.xmomen.module.authorization.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.authorization.entity.GroupPermission;
import com.xmomen.module.authorization.entity.GroupPermissionExample;
import org.apache.ibatis.annotations.Param;

public interface GroupPermissionMapper extends MybatisMapper {
    int countByExample(GroupPermissionExample example);

    int deleteByExample(GroupPermissionExample example);

    int insertSelective(GroupPermission record);

    int updateByExampleSelective(@Param("record") GroupPermission record, @Param("example") GroupPermissionExample example);
}