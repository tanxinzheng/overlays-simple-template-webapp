package com.xmomen.module.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.web.rest.WebCommonUtils;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import com.xmomen.module.shiro.token.JWTAuthenticationToken;
import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.service.UserService;
import io.jsonwebtoken.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM;
import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM;
import static org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_USERNAME_PARAM;

/**
 * Created by Jeng on 2016/1/7.
 */
public class JWTOrFormAuthenticationFilter extends AuthenticatingFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";

    private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;

//    public static final String DEFAULT_USERNAME_PARAM = "username";
//    public static final String DEFAULT_PASSWORD_PARAM = "password";
//    public static final String DEFAULT_REMEMBER_ME_PARAM = "rememberMe";
//
    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

//    private String usernameParam = DEFAULT_USERNAME_PARAM;
//    private String passwordParam = DEFAULT_PASSWORD_PARAM;
//    private String rememberMeParam = DEFAULT_REMEMBER_ME_PARAM;

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(JWTOrFormAuthenticationFilter.class);

    public JWTOrFormAuthenticationFilter() {
        setLoginUrl(DEFAULT_LOGIN_URL);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            String username = request.getParameter(DEFAULT_USERNAME_PARAM);
            String password = request.getParameter(DEFAULT_PASSWORD_PARAM);
            if(StringUtils.isEmpty(username)){
                throw new AuthenticationException("请输入用户名");
            }
            if(StringUtils.isEmpty(password)){
                throw new AuthenticationException("请输入密码");
            }
            boolean isRememberMe = isRememberMe(request);
            String host = getHost(request);
            return new UsernamePasswordToken(username, password, isRememberMe, host);
        }

        if (isLoggedAttempt(request, response)) {
            String jwtToken = getAuthzHeader(request);
            if (jwtToken != null) {
                return createToken(jwtToken);
            }
        }

        return new UsernamePasswordToken();
    }

    @Override
    public void setLoginUrl(String loginUrl) {
        String previous = getLoginUrl();
        if (previous != null) {
            this.appliedPaths.remove(previous);
        }
        super.setLoginUrl(loginUrl);
        this.appliedPaths.put(getLoginUrl(), null);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(WebCommonUtils.isJSON(request) && isLoginSubmission(request, response)){
            // 是登录请求，且Ajax或json请求
            if (log.isTraceEnabled()) {
                log.trace("Ajax Login submission detected.");
            }
            try{
                return executeLogin(request, response);
            } catch (AuthenticationException e){
                buildJSONMessage(HttpStatus.BAD_REQUEST, e.getMessage(), request, response);
                return false;
            }
        }
        if(isLoginRequest(request, response) && !WebCommonUtils.isJSON(request)) {
            // 是登录请求，页面请求
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            }else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        }
        if (log.isTraceEnabled()) {
            log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                    "Authentication url [" + getLoginUrl() + "]");
        }
        if(!WebCommonUtils.isJSON(request)){
            redirectToLogin(request, response);
        }else{
            buildJSONMessage(HttpStatus.UNAUTHORIZED, "Requires authentication", request, response);
        }
        return false;
    }

    protected boolean isRememberMe(ServletRequest request) {
        String value = request.getParameter(DEFAULT_REMEMBER_ME_PARAM);
        return value != null &&
                (value.equalsIgnoreCase("true") ||
                        value.equalsIgnoreCase("t") ||
                        value.equalsIgnoreCase("1") ||
                        value.equalsIgnoreCase("enabled") ||
                        value.equalsIgnoreCase("y") ||
                        value.equalsIgnoreCase("yes") ||
                        value.equalsIgnoreCase("on"));
    }


    protected boolean isLoggedAttempt(ServletRequest request, ServletResponse response) {
        String authzHeader = getAuthzHeader(request);
        return authzHeader != null;
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cookie : cookies) {
            if(AUTHORIZATION_HEADER.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }

    protected AuthenticationToken createToken(String token) throws Exception {
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(JWTAuthenticationToken.JWT_TOKEN_KEY).parseClaimsJws(token);
            String username = jwt.getBody().getSubject();
            return new JWTAuthenticationToken(username, token);
        } catch (SignatureException ex) {
            throw new AuthenticationException(ex);
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
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        if(token instanceof UsernamePasswordToken){
            String username = (String) subject.getPrincipal();
            String jwtToken = Jwts.builder()
                    .setSubject(username)
                    .signWith(SignatureAlgorithm.HS512, JWTAuthenticationToken.JWT_TOKEN_KEY)
                    .compact();
            httpServletResponse.addHeader(AUTHORIZATION_HEADER, jwtToken);
            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, jwtToken);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            httpServletResponse.addCookie(cookie);
            AccountModel accountModel = accountService.getAccountModelByUsername(username);
            if(accountModel != null){
                User user = new User();
                user.setId(accountModel.getUserId());
                user.setLastLoginTime(new Date());
                userService.updateUser(user);
            }
        }
        if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
            httpServletResponse.sendRedirect(getSuccessUrl());
            WebUtils.issueRedirect(request, response, getSuccessUrl());
            issueSuccessRedirect(request, response);
            return true;
        } else {
            buildJSONMessage(HttpStatus.OK, "登录成功", request, response);
        }
        return true;
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
        if (IncorrectCredentialsException.class.getSimpleName().equals(message)) {
            buildJSONMessage(HttpStatus.BAD_REQUEST, "用户名或密码错误", request, response);
        } else if (UnknownAccountException.class.getSimpleName().equals(message)) {
            buildJSONMessage(HttpStatus.BAD_REQUEST, "此用户名未注册", request, response);
        } else if (LockedAccountException.class.getSimpleName().equals(message)) {
            buildJSONMessage(HttpStatus.BAD_REQUEST, "此用户名被锁", request, response);
        } else {
            buildJSONMessage(HttpStatus.BAD_REQUEST, e.getMessage(), request, response);
        }
        return false;
    }

    private void buildJSONMessage(HttpStatus httpStatus, String message, ServletRequest request, ServletResponse response){
        try {
            Map map = new HashMap<String, Object>();
            map.put("code", httpStatus.value());
            map.put("message", message);
            map.put("timestamp", new Date());
            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
//            httpServletResponse.setContentType("text/html");
            httpServletResponse.setStatus(httpStatus.value());
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter servletOutputStream = httpServletResponse.getWriter();
            servletOutputStream.write(JSONObject.toJSONString(map));
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
            e.printStackTrace();
        }
    }

    protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
        return (request instanceof HttpServletRequest) && WebUtils.toHttp(request).getMethod().equalsIgnoreCase(POST_METHOD);
    }


    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        String className = ae.getClass().getName();
        request.setAttribute(getFailureKeyAttribute(), className);
    }

    public String getFailureKeyAttribute() {
        return failureKeyAttribute;
    }

    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }
//
//    public String getUsernameParam() {
//        return usernameParam;
//    }
//
//    public void setUsernameParam(String usernameParam) {
//        this.usernameParam = usernameParam;
//    }
//
//    public String getPasswordParam() {
//        return passwordParam;
//    }
//
//    public void setPasswordParam(String passwordParam) {
//        this.passwordParam = passwordParam;
//    }
//
//    public String getRememberMeParam() {
//        return rememberMeParam;
//    }
//
//    public void setRememberMeParam(String rememberMeParam) {
//        this.rememberMeParam = rememberMeParam;
//    }
}
