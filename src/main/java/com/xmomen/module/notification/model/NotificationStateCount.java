package com.xmomen.module.notification.model;

import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/8/25.
 */
@Data
public class NotificationStateCount implements Serializable {

    @DictionaryInterpreter(index = DictionaryIndex.NOTIFICATION_DATA_STATE, fieldName = "dataStateDesc")
    private String dataState;
    private Integer number;
}
