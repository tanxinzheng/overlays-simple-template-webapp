package com.xmomen.framework.web.rest;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeng on 15/11/29.
 */
@Data
public class RestError {

    private Date timestamp;
    private String message;
    private Integer status;
    private List<FieldError> errors;
    private String path;
    @JsonIgnore
    private String exception;

    public RestError() {
    }

    public RestError(String message) {
        this.timestamp = new Date();
        this.message = message;
        this.status = HttpStatus.OK.value();
    }

    public RestError(Exception ex) {
        this.timestamp = new Date();
        this.message = ex.getMessage();
        this.exception = ex.getClass().getName();
    }

    public RestError(Exception ex, HttpServletRequest request) {
        this.timestamp = new Date();
        this.path = request.getRequestURI();
        this.message = ex.getMessage();
        this.exception = ex.getClass().getName();
    }

    public static RestError build(String message, Exception ex, HttpServletRequest request) {
        RestError restError = new RestError();
        restError.setTimestamp(new Date());
        if(message != null){
            restError.setMessage(message);
        }
        if(request != null){
            restError.setPath(request.getRequestURI());
        }
        if(ex != null){
            restError.setException(ex.getClass().getName());
        }
        return restError;
    }

    public static RestError build(Exception ex, HttpServletRequest request) {
        if(ex == null){
            return build("request error", null, request);
        }
        return build(ex.getMessage(), ex, request);
    }

    public static RestError build(Exception ex) {
        if(ex == null){
            return build("request error");
        }
        return build(ex.getMessage(), ex, null);
    }

    public static RestError build(String message) {
        return build(message, null, null);
    }

    public String toJSONString(){
        return JSONObject.toJSONString(this);
    }

}
