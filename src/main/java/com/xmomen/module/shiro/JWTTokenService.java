package com.xmomen.module.shiro;

import com.xmomen.module.shiro.token.JWTAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tanxinzheng on 17/6/15.
 */
public interface JWTTokenService {

    /**
     * 获取token
     * @param request
     * @param response
     * @return
     */
    public JWTAuthenticationToken getToken(HttpServletRequest request, HttpServletResponse response);

    /**
     * 设置token
     * @param request
     * @param response
     * @param token
     */
    public void setToken(HttpServletRequest request, HttpServletResponse response, String token);
}
