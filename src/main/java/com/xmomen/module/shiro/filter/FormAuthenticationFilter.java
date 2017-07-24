package com.xmomen.module.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.web.rest.RestError;
import com.xmomen.framework.web.rest.WebCommonUtils;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import com.xmomen.module.user.model.User;
import com.xmomen.module.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Jeng on 2016/1/7.
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    private void buildJSONMessage(RestError message, HttpStatus status, ServletRequest request, ServletResponse response){
        try {
            message.setTimestamp(new Date());
            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setStatus(status.value());
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            servletOutputStream.print(JSONObject.toJSONString(message));
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
            e.printStackTrace();
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
                    //allow them to see the login page ;)
                    return true;
                } else {
                    RestError restError = new RestError();
                    restError.setMessage("Requires authentication");
                    restError.setStatus(HttpStatus.UNAUTHORIZED.value());
                    buildJSONMessage(restError, HttpStatus.UNAUTHORIZED, request, response);
                }
                return false;
            }
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
                saveRequestAndRedirectToLogin(request, response);
            } else {
                RestError restError = new RestError();
                restError.setMessage("Requires authentication");
                restError.setStatus(HttpStatus.UNAUTHORIZED.value());
                buildJSONMessage(restError, HttpStatus.UNAUTHORIZED, request, response);
            }
            return false;
        }
    }

    /**
     * 登录成功处理（兼容自动识别异步请求，json请求及页面请求）
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        String username = (String) subject.getPrincipal();
        AccountModel accountModel = accountService.getAccountModelByUsername(username);
        if(accountModel != null){
            User user = new User();
            user.setLastLoginTime(new Date());
            user.setId(accountModel.getUserId());
            userService.updateUser(user);
        }
        subject.getSession().setAttribute(accountService.getSessionModelKey(), accountModel);
        if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
            issueSuccessRedirect(request, response);
        } else {
            RestError restError = new RestError();
            restError.setMessage("Login success");
            restError.setStatus(HttpStatus.OK.value());
            buildJSONMessage(restError, HttpStatus.UNAUTHORIZED, request, response);
        }
        return false;
    }

    /**
     * 登录失败处理（兼容自动识别异步请求，json请求及页面请求）
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
            setFailureAttribute(request, e);
            return true;
        }
        String message = e.getClass().getSimpleName();
        RestError restError = new RestError();
        if (IncorrectCredentialsException.class.getSimpleName().equals(message)) {
            restError.setMessage("密码错误");
        } else if (UnknownAccountException.class.getSimpleName().equals(message)) {
            restError.setMessage("账号不存在");
        } else if (LockedAccountException.class.getSimpleName().equals(message)) {
            restError.setMessage("账号被锁定");
        } else {
            restError.setMessage("未知错误");
        }
        buildJSONMessage(restError, HttpStatus.UNAUTHORIZED, request, response);
        return false;
    }
}
