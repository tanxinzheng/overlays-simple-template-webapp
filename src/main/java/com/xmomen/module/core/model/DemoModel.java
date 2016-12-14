package com.xmomen.module.core.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xmomen.framework.web.json.CustomDateDeserialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tanxinzheng on 16/12/11.
 */
@Data
public class DemoModel {

    private Integer age;
    private String name;
    @JsonDeserialize(using = CustomDateDeserialize.class)
    private Date createTime;
    private Date updateTime;
    private BigDecimal amount;
}
