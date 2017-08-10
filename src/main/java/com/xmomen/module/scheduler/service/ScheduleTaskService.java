package com.xmomen.module.scheduler.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.scheduler.model.ScheduleTaskModel;
import com.xmomen.module.scheduler.model.ScheduleTaskQuery;

/**
 * Created by tanxinzheng on 17/8/9.
 */
public interface ScheduleTaskService {

    /**
     * 查询调度任务
     * @param scheduleJobQuery
     * @return
     */
    Page<ScheduleTaskModel> getScheduleTaskPages(ScheduleTaskQuery scheduleJobQuery);
}
