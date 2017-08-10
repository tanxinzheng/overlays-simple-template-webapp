package com.xmomen.module.scheduler.job;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by tanxinzheng on 17/8/8.
 */
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("测试任务，测试时间：" + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
    }
}
