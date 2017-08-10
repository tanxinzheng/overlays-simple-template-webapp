package com.xmomen.framework.web.rest;

import org.springframework.http.MediaType;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jeng on 2016/3/1.
 */
public class WebCommonUtils {

    public static boolean isJSON(ServletRequest request){
        if("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))
                || "application/json".equalsIgnoreCase(request.getContentType())){
            return true;
        }
        return false;
    }

    public static boolean isJSON(WebRequest request){
        String xHeader = request.getHeader("X-Requested-With");
        String application = request.getHeader("content-type");
        if("XMLHttpRequest".equalsIgnoreCase(xHeader)
                || MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(application)
                || MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(application)){
            return true;
        }
        return false;
    }
}
