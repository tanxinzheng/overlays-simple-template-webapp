package com.xmomen.module.security;

import com.google.common.collect.Maps;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tanxinzheng on 17/8/18.
 */
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    public static final String HEADER_AUTHORIZATION_NAME = "Authorization";

    public static final String JWT_TOKEN_SESSION_KEY = "jwt-token-cache";

    public static final String JWT_TOKEN_COOKIE_KEY = "jwt-token";

    @Value("${jwt.secret}")
    private String secret;
    @Autowired
    AccountService accountService;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    EhCacheCacheManager ehCacheCacheManager;

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    @Override
    public String getToken(HttpServletRequest request) {
        HttpServletRequest httpRequest = request;
        String token = httpRequest.getHeader(HEADER_AUTHORIZATION_NAME);
        if(token == null){
            token = request.getParameter("token");
        }
        if(token == null && ArrayUtils.isNotEmpty(request.getCookies())){
            for (Cookie cookie : request.getCookies()) {
                if(JWT_TOKEN_COOKIE_KEY.equalsIgnoreCase(cookie.getName())){
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }


    /**
     * 设置token
     *
     * @param request
     * @param response
     * @param username
     */
    @Override
    public void setToken(HttpServletRequest request, HttpServletResponse response, String username) {
        AccountModel accountModel = accountService.getAccountModelByUsername(username);
        Set<String> permissions = accountService.findPermissions(accountModel.getUserId());
        Set<String> roles = accountService.findRoles(accountModel.getUserId());
        Map<String, Object> data = Maps.newHashMap();
        data.put("username", username);
        data.put("account", accountModel);
        data.put("permissions", permissions.stream().map(String::toString).collect(Collectors.toList()));
        data.put("roles", roles.stream().map(String::toString).collect(Collectors.toList()));
        String token = createToken(username, secret);
        Cache cache = cacheManager.getCache(JWT_TOKEN_SESSION_KEY);
        cache.put(token, data);
        ehCacheCacheManager.getCacheManager().getCache(JWT_TOKEN_SESSION_KEY).flush();
        response.addHeader(HEADER_AUTHORIZATION_NAME, token);
        Cookie cookie = new Cookie(JwtTokenServiceImpl.JWT_TOKEN_COOKIE_KEY, token);
        cookie.setSecure(Boolean.TRUE);
        response.addCookie(cookie);
    }

    public String createToken(String username, String secret){
        Claims claims = Jwts.claims().setSubject(username);
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    /**
     * 校验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean validToken(HttpServletRequest request) {
        try {
            String token = getToken(request);
            if(token == null){
                return false;
            }
            Cache cache = cacheManager.getCache(JWT_TOKEN_SESSION_KEY);
            HashMap data = cache.get(token, HashMap.class);
            if(data == null){
                // session会话超时
                return false;
            }
            // 延长session会话时间
            cache.put(token, data);
            ehCacheCacheManager.getCacheManager().getCache(JWT_TOKEN_SESSION_KEY).flush();
            return true;
        } catch (Exception e) {
            throw new JwtException(e.getMessage(), e);
        }
    }

    /**
     * 删除token
     *
     * @param request
     */
    @Override
    public void removeToken(HttpServletRequest request, HttpServletResponse response) {
        String token = getToken(request);
        Cache cache = cacheManager.getCache(JWT_TOKEN_SESSION_KEY);
        Arrays.stream(request.getCookies()).forEach(cookie -> {
            if(JWT_TOKEN_COOKIE_KEY.equalsIgnoreCase(cookie.getName())){
                cookie.setValue(null);
                cookie.setMaxAge(0);
            }
            response.addCookie(cookie);
        });
        cache.evict(token);
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        Cache cache = cacheManager.getCache(JWT_TOKEN_SESSION_KEY);
        Cache.ValueWrapper valueWrapper = cache.get(token);
        if(valueWrapper == null) {
            return null;
        }
        Map data = (Map) valueWrapper.get();
        if(data == null){
            return null;
        }
        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
        List<String> permissions = (List<String>) data.get("permissions");
        if(!CollectionUtils.isEmpty(permissions)){
            for (String permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        }
        JwtUser jwtUser = new JwtUser((String) data.get("username"), token, authorities);
        return new UsernamePasswordAuthenticationToken(
                jwtUser.getUsername(), token, authorities);
    }
}
