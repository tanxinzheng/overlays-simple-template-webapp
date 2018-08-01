package com.xmomen.module.notification.mapper;

import com.xmomen.module.notification.model.Notification;
import com.xmomen.module.notification.model.NotificationModel;
import com.xmomen.module.notification.model.NotificationQuery;
import com.xmomen.module.notification.model.NotificationStateCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public interface NotificationMapper {

    List<Notification> select(NotificationQuery notificationQuery);

    List<NotificationModel> selectModel(NotificationQuery notificationQuery);

    Notification selectByPrimaryKey(String primaryKey);

    NotificationModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(Notification record);

    int updateSelective(Notification record);

    int updateSelectiveByQuery(@Param("record") Notification record, @Param("query") NotificationQuery example);

    List<NotificationStateCount> countNotificationState(NotificationQuery notificationQuery);
}
