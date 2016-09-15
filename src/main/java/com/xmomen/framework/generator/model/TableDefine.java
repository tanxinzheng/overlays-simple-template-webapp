package com.xmomen.framework.generator.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by tanxinzheng on 16/9/12.
 */
public @Data class TableDefine implements Serializable {

    private String name;
    private String tableName;
    private String comment;
    private String templateType;
    private HashMap<String, TableDefine> fields;
    private String targetPackageUrl;
    private String targetPackageName;
    private String domainName;

    public @Data class TableFieldDefine implements Serializable {
        // 字段名称
        private String fieldName;
        // 字段描述
        private String fieldDesc;
        // java类型
        private String javaType;
        // 是否为主键
        private boolean isPrimary;
        // 组件类型
        private ComponentType componentType;
    }

}
