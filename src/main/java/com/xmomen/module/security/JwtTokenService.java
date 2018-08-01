package com.xmomen.module.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tanxinzheng on 17/6/15.
 */
public interface JwtTokenService {

    /**
     * 获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request);

    /**
     * 刷新token
     * @param request
     */
    public void refreshToken(HttpServletRequest request);

    /**
     * 获取Authentication
     * @param request
     * @return
     */
    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request);

    /**
     * 获取Authentication
     * @param token
     * @return
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String token);

    /**
     * 设置token
     * @param request
     * @param response
     * @param username
     */
    public void setToken(HttpServletRequest request, HttpServletResponse response, String username);

    /**
     * 校验token
     * @param request
     * @return
     */
    public boolean validToken(HttpServletRequest request);

    /**
     * 删除token
     * @param request
     * @param response
     */
    void removeToken(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取用户名
     * @param token
     * @return
     */
    String getUsername(String token);
}
