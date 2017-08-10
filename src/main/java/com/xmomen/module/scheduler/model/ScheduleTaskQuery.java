package com.xmomen.module.scheduler.model;

import com.xmomen.framework.model.BaseQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/8/9.
 */
@Data
public class ScheduleTaskQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String jobName;
    private String jobGroup;
    private String triggerState;
}
