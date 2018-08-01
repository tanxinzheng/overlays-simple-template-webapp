package com.xmomen.framework.web.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeng on 15/11/29.
 */
@Data
public class RestError extends RestResult {

    private List<FieldError> errors;
    @JsonIgnore
    private String exception;

    public RestError() {
    }

    public RestError(String message) {
        RestResult.OK(message);
    }

    public RestError(Exception ex) {
        setTimestamp(new Date());
        setMessage(ex.getMessage());
        this.exception = ex.getClass().getName();
    }

    public RestError(Exception ex, HttpServletRequest request) {
        setTimestamp(new Date());
        setPath(request.getRequestURI());
        setMessage(ex.getMessage());
        setException(ex.getClass().getName());
    }

    public static RestError build(String message, Exception ex, HttpServletRequest request, HttpServletResponse response) {
        RestError restError = new RestError();
        restError.setTimestamp(new Date());
        if(message != null){
            restError.setMessage(message);
        }
        if(request != null){
            restError.setPath(request.getRequestURI());
        }
        if(response != null){
            restError.setStatus(response.getStatus());
        }
        if(ex != null){
            restError.setException(ex.getClass().getName());
        }
        return restError;
    }

    public static RestError build(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        if(ex == null){
            return build("request error", null, request, response);
        }
        return build(ex.getMessage(), ex, request, response);
    }

    public static RestError build(Exception ex) {
        if(ex == null){
            return build("request error");
        }
        return build(ex.getMessage(), ex, null, null);
    }

    public static RestError build(String message) {
        return build(message, null, null, null);
    }

}
