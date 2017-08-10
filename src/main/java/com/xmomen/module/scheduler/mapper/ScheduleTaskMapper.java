package com.xmomen.module.scheduler.mapper;

import com.xmomen.module.scheduler.model.ScheduleTaskModel;
import com.xmomen.module.scheduler.model.ScheduleTaskQuery;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
public interface ScheduleTaskMapper {


    List<ScheduleTaskModel> selectModel(ScheduleTaskQuery scheduleJobQuery);
}
