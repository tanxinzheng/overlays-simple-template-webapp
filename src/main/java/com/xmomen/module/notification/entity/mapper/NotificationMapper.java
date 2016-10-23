package com.xmomen.module.notification.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.notification.entity.Notification;
import com.xmomen.module.notification.entity.NotificationExample;
import org.apache.ibatis.annotations.Param;

public interface NotificationMapper extends MybatisMapper {
    int countByExample(NotificationExample example);

    int deleteByExample(NotificationExample example);

    int insertSelective(Notification record);

    int updateByExampleSelective(@Param("record") Notification record, @Param("example") NotificationExample example);
}