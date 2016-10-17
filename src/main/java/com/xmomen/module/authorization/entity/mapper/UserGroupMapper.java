package com.xmomen.module.authorization.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.authorization.entity.UserGroup;
import com.xmomen.module.authorization.entity.UserGroupExample;
import org.apache.ibatis.annotations.Param;

public interface UserGroupMapper extends MybatisMapper {
    int countByExample(UserGroupExample example);

    int deleteByExample(UserGroupExample example);

    int insertSelective(UserGroup record);

    int updateByExampleSelective(@Param("record") UserGroup record, @Param("example") UserGroupExample example);
}