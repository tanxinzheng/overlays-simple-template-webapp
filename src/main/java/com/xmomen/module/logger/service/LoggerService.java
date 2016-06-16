package com.xmomen.module.logger.service;

import com.xmomen.module.logger.LogModel;
import com.xmomen.module.logger.mapper.LoggerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Jeng on 16/6/13.
 */
@Service
public class LoggerService {

    @Autowired
    LoggerMapper loggerMapper;

    public void setLogInfo(String actionName, String userId, String clientId, String actionParams, String actionResult) {
        LogModel logModel = new LogModel();
        logModel.setActionName(actionName);
        logModel.setUserId(userId);
        logModel.setClientIp(clientId);
        logModel.setActionDate(new Date());
        logModel.setActionParams(actionParams);
        logModel.setActionResult(actionResult);
        loggerMapper.insertLog(logModel);
    }

    public String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}
