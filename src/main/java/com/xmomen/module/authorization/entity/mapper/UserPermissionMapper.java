package com.xmomen.module.authorization.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.authorization.entity.UserPermission;
import com.xmomen.module.authorization.entity.UserPermissionExample;
import org.apache.ibatis.annotations.Param;

public interface UserPermissionMapper extends MybatisMapper {
    int countByExample(UserPermissionExample example);

    int deleteByExample(UserPermissionExample example);

    int insertSelective(UserPermission record);

    int updateByExampleSelective(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);
}