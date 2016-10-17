package com.xmomen.module.authorization.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.authorization.entity.Group;
import com.xmomen.module.authorization.entity.GroupExample;
import org.apache.ibatis.annotations.Param;

public interface GroupMapper extends MybatisMapper {
    int countByExample(GroupExample example);

    int deleteByExample(GroupExample example);

    int insertSelective(Group record);

    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);
}