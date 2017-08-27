package com.xmomen.module.notification.mapper;

import com.xmomen.module.notification.model.NotificationSend;
import com.xmomen.module.notification.model.NotificationSendModel;
import com.xmomen.module.notification.model.NotificationSendQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public interface NotificationSendMapper {

    List<NotificationSend> select(NotificationSendQuery notificationSendQuery);

    List<NotificationSendModel> selectModel(NotificationSendQuery notificationSendQuery);

    NotificationSend selectByPrimaryKey(String primaryKey);

    NotificationSendModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(NotificationSend record);

    int updateSelective(NotificationSend record);

    int updateSelectiveByQuery(@Param("record") NotificationSend record, @Param("query") NotificationSendQuery example);
}
