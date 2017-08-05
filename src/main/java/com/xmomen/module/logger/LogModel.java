package com.xmomen.module.logger;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeng on 16/3/20.
 */
public @Data
class LogModel implements Serializable{

    private String userId;
    private String actionName;
    private Date actionDate;
    private String clientIp;
    private String targetClass;
    private String targetMethod;
    private String actionParams;
    private String actionResult;
    private String remark;

}
