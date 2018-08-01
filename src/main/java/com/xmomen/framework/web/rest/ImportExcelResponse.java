package com.xmomen.framework.web.rest;

import lombok.Data;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by tanxinzheng on 18/5/15.
 */
@Data
public class ImportExcelResponse {

    private boolean success;
    private Date uploadDate;
    private Long total;
    private String message;


    public static ImportExcelResponse fail(){
        ImportExcelResponse importExcelResponse = new ImportExcelResponse();
        importExcelResponse.setSuccess(Boolean.FALSE);
        importExcelResponse.setUploadDate(new Date());
        importExcelResponse.setMessage("导入失败");
        return importExcelResponse;
    }

    public static ImportExcelResponse success(Integer total){
        return success(Long.valueOf(total));
    }

    public static ImportExcelResponse success(Long total){
        ImportExcelResponse importExcelResponse = new ImportExcelResponse();
        importExcelResponse.setTotal(total);
        importExcelResponse.setSuccess(Boolean.TRUE);
        importExcelResponse.setUploadDate(new Date());
        importExcelResponse.setMessage(MessageFormat.format("成功导入{0}条数据", total));
        return importExcelResponse;
    }
}
