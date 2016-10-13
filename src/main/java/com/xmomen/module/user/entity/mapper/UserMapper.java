package com.xmomen.module.user.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.user.entity.User;
import com.xmomen.module.user.entity.UserExample;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends MybatisMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int insertSelective(User record);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);
}