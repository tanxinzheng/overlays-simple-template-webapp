package com.xmomen.module.logger.model;

import com.xmomen.framework.model.BaseQuery;
import lombok.Data;

import java.util.Date;

/**
 * Created by tanxinzheng on 17/8/5.
 */
@Data
public class ActionLogQuery extends BaseQuery {

    private String userId;
    private Date startActionDate;
    private Date endActionDate;
}
