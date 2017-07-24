package com.xmomen.module.core.model;

import com.xmomen.framework.model.BaseQuery;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tanxinzheng on 17/6/28.
 */
@Data
public class SelectOptionQuery extends BaseQuery {
    @NotBlank
    private String type;
    private String parentId;
}
