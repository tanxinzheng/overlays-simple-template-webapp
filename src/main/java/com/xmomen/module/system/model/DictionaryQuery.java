package com.xmomen.module.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
public @Data class DictionaryQuery implements Serializable {

    private String keyword;
    private String id;
    private String[] ids;
    private String[] excludeIds;

}
