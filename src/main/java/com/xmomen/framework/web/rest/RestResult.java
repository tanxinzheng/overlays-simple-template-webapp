package com.xmomen.framework.web.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tanxinzheng on 2018/6/10.
 */
@Data
public class RestResult implements Serializable {

    private Date timestamp;
    private String message;
    private Integer status;
    private String path;
    private Object data;

    public RestResult() {
    }

    public static RestResult OK(String message) {
        RestResult restResult = new RestResult();
        restResult.setMessage(message);
        restResult.setStatus(HttpStatus.OK.value());
        restResult.setTimestamp(new Date());
        return restResult;
    }
}
