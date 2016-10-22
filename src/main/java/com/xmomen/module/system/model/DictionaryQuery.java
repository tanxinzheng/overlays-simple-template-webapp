package com.xmomen.module.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-20 23:14:12
 * @version 1.0.0
 */
public @Data class DictionaryQuery implements Serializable {
    /** 关键字 */
    private String keyword;
    /** 主键 */
    private String id;
    /** 包含主键集 */
    private String[] ids;
    /** 排除主键集 */
    private String[] excludeIds;

}
