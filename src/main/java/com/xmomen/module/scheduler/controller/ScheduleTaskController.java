package com.xmomen.module.scheduler.controller;

import io.swagger.annotations.ApiOperation;
import com.xmomen.framework.logger.ActionLog;
import com.github.pagehelper.Page;
import com.xmomen.module.scheduler.QuartzManager;
import com.xmomen.module.scheduler.model.ScheduleTaskModel;
import com.xmomen.module.scheduler.model.ScheduleTaskQuery;
import com.xmomen.module.scheduler.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by tanxinzheng on 17/8/9.
 */
@RestController
@RequestMapping(value = "/schedule/task")
public class ScheduleTaskController {


    @Autowired
    ScheduleTaskService scheduleTaskService;

    /**
     * 查询调度任务
     * @param scheduleJobQuery
     * @return
     */
    @ApiOperation(value = "查询定时任务")
    @ActionLog(actionName = "查询定时任务")
    @RequestMapping(method = RequestMethod.GET)
    public Page<ScheduleTaskModel> getScheduleTaskList(ScheduleTaskQuery scheduleJobQuery) {
        return scheduleTaskService.getScheduleTaskPages(scheduleJobQuery);
    }

    /**
     * 查询调度任务
     * @param scheduleTaskModel
     * @return
     */
    @ApiOperation(value = "修改定时任务")
    @ActionLog(actionName = "修改定时任务")
    @RequestMapping(method = RequestMethod.PUT)
    public void updateScheduleTask(@Valid ScheduleTaskModel scheduleTaskModel) {
        scheduleTaskService.updateScheduleTask(scheduleTaskModel);
    }

    @Autowired(required = false)
    QuartzManager quartzManager;

    /**
     * 修改调度任务
     * @param jobName
     * @return
     */
    @ApiOperation(value = "修改调度任务")
    @ActionLog(actionName = "修改调度任务")
    @RequestMapping(value = "/{jobName}", method = RequestMethod.PUT)
    public void updateScheduleTask(@PathVariable(value = "jobName") String jobName,
                                   @RequestParam(value = "action") Integer action,
                                   @RequestParam(value = "triggerState", required = false) String triggerState,
                                   @RequestParam(value = "cronExpression", required = false) String cronExpression) {
        switch (action){
            case 1://启动
                quartzManager.resumeJob(jobName);
                break;
            case 2://暂停
                quartzManager.pauseJob(jobName);
                break;
            case 3://重启
                quartzManager.resumeJob(jobName);
                break;
            case 4://立即执行
                quartzManager.triggerJob(jobName);
                break;
            case 5://更新时间
                quartzManager.updateCronExpress(jobName, cronExpression);
                break;
            case 9://删除
                quartzManager.deleteJob(jobName);
                break;
        }

    }
}
