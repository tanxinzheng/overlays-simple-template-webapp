package com.xmomen.module.core.model;

import com.xmomen.framework.model.BaseQuery;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tanxinzheng on 17/6/28.
 */
@Data
public class SelectOptionQuery extends BaseQuery {
    @NotBlank(message = "typeCode为必填项")
    private String typeCode;
    private String parentId;
    private String keyword;
}
