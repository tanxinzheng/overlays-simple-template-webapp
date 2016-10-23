package com.xmomen.module.notification.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.notification.entity.NotificationText;
import com.xmomen.module.notification.entity.NotificationTextExample;
import org.apache.ibatis.annotations.Param;

public interface NotificationTextMapper extends MybatisMapper {
    int countByExample(NotificationTextExample example);

    int deleteByExample(NotificationTextExample example);

    int insertSelective(NotificationText record);

    int updateByExampleSelective(@Param("record") NotificationText record, @Param("example") NotificationTextExample example);
}