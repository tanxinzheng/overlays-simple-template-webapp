package com.xmomen.module.scheduler.model;

import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tanxinzheng on 17/8/9.
 */
@Data
public class ScheduleTaskModel implements Serializable {

    private String jobName;
    private String jobClassName;
    private String jobGroup;
    private String description;
    @NotNull(message = "triggerGroup不能为空")
    private String triggerGroup;
    @NotNull(message = "triggerName不能为空")
    private String triggerName;
    private String startTime;
    private String endTime;
    @DictionaryInterpreter(index = DictionaryIndex.TRIGGER_STATE, fieldName = "triggerStateName")
    private String triggerState;
    private String prevFireTime;
    private String nextFireTime;
    private String cronExpression;

}
