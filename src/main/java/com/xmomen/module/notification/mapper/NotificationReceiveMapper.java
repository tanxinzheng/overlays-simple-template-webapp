package com.xmomen.module.notification.mapper;

import com.xmomen.module.notification.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public interface NotificationReceiveMapper {

    List<NotificationReceive> select(NotificationReceiveQuery notificationReceiveQuery);

    List<NotificationReceiveModel> selectModel(NotificationReceiveQuery notificationReceiveQuery);

    NotificationReceive selectByPrimaryKey(String primaryKey);

    NotificationReceiveModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(NotificationReceive record);

    int updateSelective(NotificationReceive record);

    int updateSelectiveByQuery(@Param("record") NotificationReceive record, @Param("query") NotificationReceiveQuery example);

    List<NotificationModel> selectNotification(NotificationQuery notificationQuery);
}
