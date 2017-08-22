package com.xmomen.module.security;

import io.jsonwebtoken.JwtException;
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
     * 获取Authentication
     * @param request
     * @return
     */
    public Authentication getAuthentication(HttpServletRequest request);

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
    public boolean validToken(HttpServletRequest request) throws JwtException;

    /**
     * 删除token
     * @param request
     */
    void removeToken(HttpServletRequest request);
}
