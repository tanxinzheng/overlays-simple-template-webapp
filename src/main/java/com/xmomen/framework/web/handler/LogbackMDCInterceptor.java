package com.xmomen.framework.web.handler;

import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tanxinzheng on 18/2/9.
 */
public class LogbackMDCInterceptor extends HandlerInterceptorAdapter {

    /**
     * 会话ID
     */
    private final static String USER_ID = "userId";

    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // 删除
        MDC.remove(USER_ID);
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Authentication subject = SecurityContextHolder.getContext().getAuthentication();
        if(subject != null && subject.getPrincipal() != null){
            String username = subject.getPrincipal().toString();
            // 放SessionId
            MDC.put(USER_ID, username);
        }
        return true;
    }

}
