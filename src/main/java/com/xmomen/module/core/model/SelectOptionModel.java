package com.xmomen.module.core.model;

import lombok.Data;

/**
 * Created by tanxinzheng on 17/6/28.
 */
@Data
public class SelectOptionModel {
    private String typeCode;
    private String typeName;
    private String code;
    private String name;
    private int sort;

    public SelectOptionModel() {
    }

    public SelectOptionModel(String typeCode, String typeName, String code, String name, int sort) {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.code = code;
        this.name = name;
        this.sort = sort;
    }

    public SelectOptionModel(String typeCode, String typeName, String code, String name) {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.code = code;
        this.name = name;
    }
}
