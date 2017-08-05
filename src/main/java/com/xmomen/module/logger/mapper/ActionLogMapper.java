package com.xmomen.module.logger.mapper;

import com.xmomen.module.logger.LogModel;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by Jeng on 16/3/20.
 */
public interface ActionLogMapper {

    void insertActionLog(LogModel logModel);
}
