package com.xmomen.framework.web.rest;

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

}
