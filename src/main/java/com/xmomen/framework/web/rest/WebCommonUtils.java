package com.xmomen.framework.web.rest;

import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by Jeng on 2016/3/1.
 */
public class WebCommonUtils implements Serializable {

    public static final WebCommonUtils INSTANCE = new WebCommonUtils();

    public static WebCommonUtils getInstance(){
        return INSTANCE;
    }

    /**
     * 是否JSON请求
     * @param request
     * @return
     */
    public static boolean isJSON(ServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))
                || MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getContentType())
                || MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType());
    }
}
