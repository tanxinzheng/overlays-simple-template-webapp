package com.xmomen.module.logger.service;

import com.github.pagehelper.Page;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.module.logger.LogModel;
import com.xmomen.module.logger.mapper.ActionLogMapper;
import com.xmomen.module.logger.model.ActionLogQuery;
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
    ActionLogMapper actionLogMapper;

    public void setLogInfo(LogModel logInfo) {
        actionLogMapper.insertActionLog(logInfo);
    }

    /**
     * 查询操作记录
     * @param actionLogQuery
     * @return
     */
    public Page<LogModel> getActionLogPage(ActionLogQuery actionLogQuery){
        PageInterceptor.startPage(actionLogQuery);
        actionLogMapper.getActionLogs(actionLogQuery);
        return PageInterceptor.endPage();
    }

}
