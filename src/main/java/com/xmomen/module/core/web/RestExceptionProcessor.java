package com.xmomen.module.core.web;

import com.xmomen.framework.web.rest.RestError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by tanxinzheng on 16/10/19.
 */
@ControllerAdvice
public class RestExceptionProcessor {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> requestMethodNotSupported(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage("error.patient.bad.id", null, locale);

        String errorURL = req.getRequestURL().toString();

        RestError errorInfo = new RestError(ex, req);
        return new ResponseEntity<Object>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> requestHandlingMethodNotSupported(HttpServletRequest req, NoSuchRequestHandlingMethodException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage("error.patient.bad.id", null, locale);

        String errorURL = req.getRequestURL().toString();

        RestError errorInfo = new RestError(ex, req);
        return new ResponseEntity<Object>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
