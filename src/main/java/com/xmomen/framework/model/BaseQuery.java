package com.xmomen.framework.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/6/6.
 */
@Data
public class BaseQuery implements Serializable {

    private Integer pageSize;
    private Integer pageNum;
    private String orderBy;
    // 排序字段（-为倒序），如xxx?sort=-user,created_time
    private String[] sorts;
    // 只显示需要的字段
    private String[] fields;

    public void setDefaultPage(){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
    }

    public void setSorts(String[] sorts) {
        this.sorts = sorts;
        if(sorts != null && sorts.length > 0){
            String order = sorts[0];
            if(order.startsWith("-")){
                this.orderBy = order.substring(1,order.length()) + " desc";
            }else{
                this.orderBy = order;
            }
        }
    }

}
