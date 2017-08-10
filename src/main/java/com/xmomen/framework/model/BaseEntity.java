package com.xmomen.framework.model;

import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tanxinzheng on 17/6/7.
 */
@Data
public class BaseEntity implements Serializable {

    @DictionaryInterpreter(fieldName = "createdUserName", index = DictionaryIndex.USER_ID)
    private String createdUserId;
    private Date createdTime;
    @DictionaryInterpreter(fieldName = "updatedUserName", index = DictionaryIndex.USER_ID)
    private String updatedUserId;
    private Date updatedTime;
    private Integer dataVersion;

}
