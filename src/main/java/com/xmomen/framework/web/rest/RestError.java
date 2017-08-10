package com.xmomen.framework.web.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeng on 15/11/29.
 */
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

}
