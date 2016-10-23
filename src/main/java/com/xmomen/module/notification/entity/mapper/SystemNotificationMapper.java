package com.xmomen.module.notification.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.notification.entity.SystemNotification;
import com.xmomen.module.notification.entity.SystemNotificationExample;
import org.apache.ibatis.annotations.Param;

public interface SystemNotificationMapper extends MybatisMapper {
    int countByExample(SystemNotificationExample example);

    int deleteByExample(SystemNotificationExample example);

    int insertSelective(SystemNotification record);

    int updateByExampleSelective(@Param("record") SystemNotification record, @Param("example") SystemNotificationExample example);
}