package com.xmomen.module.logger.mapper;

import com.xmomen.module.logger.LogModel;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by Jeng on 16/3/20.
 */
public interface LoggerMapper {

    @Insert("insert into xmo_log_info(id, user_id,action_name,action_date,action_params,client_ip,action_result) values(replace(UUID(),'-',''), #{userId},#{actionName},#{actionDate},#{actionParams},#{clientIp},#{actionResult}) ")
    void insertLog(LogModel logModel);
}
