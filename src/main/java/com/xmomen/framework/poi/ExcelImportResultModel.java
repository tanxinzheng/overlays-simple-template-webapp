package com.xmomen.framework.poi;

import com.xmomen.framework.web.rest.RestError;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tanxinzheng on 16/12/10.
 */
@Data
public class ExcelImportResultModel extends RestError {

    public ExcelImportResultModel(Exception ex, HttpServletRequest request) {
        super(ex, request);
    }

    /**
     * 校验失败的excel下载结果（可根据此下载的excel修改后重新导入）
     */
    private String validResultUrl;

}


