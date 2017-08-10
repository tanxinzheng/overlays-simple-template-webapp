package com.xmomen.module.scheduler.model;

import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/8/9.
 */
@Data
public class ScheduleTaskModel implements Serializable {

    private String jobName;

    private String jobGroup;
    private String description;
    @DictionaryInterpreter(index = DictionaryIndex.TRIGGER_STATE, fieldName = "triggerStateName")
    private String triggerState;
    private String nextFireTime;
    private String cronExpression;

}
