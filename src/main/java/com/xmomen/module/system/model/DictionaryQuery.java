package com.xmomen.module.system.model;

import lombok.Data;
import com.xmomen.framework.model.BaseQuery;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 1:07:45
 * @version 1.0.0
 */
public @Data class DictionaryQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String parentId;
    private String id;
    private String[] ids;
    private String[] excludeIds;
    private String code;
    private String type;

}
