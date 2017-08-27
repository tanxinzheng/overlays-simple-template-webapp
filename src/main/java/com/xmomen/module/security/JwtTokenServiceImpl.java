package com.xmomen.module.security;

import com.google.common.collect.Maps;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String token = httpRequest.getHeader(HEADER_AUTHORIZATION_NAME);
        if(token == null){
            token = request.getParameter("token");
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
        Claims claims = Jwts.claims().setSubject(username);
        Map<String, Object> data = Maps.newHashMap();
        data.put("username", username);
        data.put("account", accountModel);
        data.put("permissions", permissions.stream().map(s -> s.toString()).collect(Collectors.toList()));
        data.put("roles", roles.stream().map(s -> s.toString()).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        Cache cache = cacheManager.getCache(JWT_TOKEN_SESSION_KEY);
        cache.put(token, data);
        ehCacheCacheManager.getCacheManager().getCache(JWT_TOKEN_SESSION_KEY).flush();
        response.addHeader(HEADER_AUTHORIZATION_NAME, token);
    }

    /**
     * 校验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean validToken(HttpServletRequest request) throws JwtException {
        try {
            String token = getToken(request);
            if(token == null){
                return false;
            }
            Jws<Claims> jwt = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
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
    public void removeToken(HttpServletRequest request) {
        String token = getToken(request);
        Cache cache = cacheManager.getCache(JWT_TOKEN_SESSION_KEY);
        cache.evict(token);
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        Jws<Claims> jwt = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        Cache cache = cacheManager.getCache(JWT_TOKEN_SESSION_KEY);
        Cache.ValueWrapper valueWrapper = cache.get(token);
        Map data = (Map) valueWrapper.get();
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        List<String> permissions = (List<String>) data.get("permissions");
        if(!CollectionUtils.isEmpty(permissions)){
            for (String permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        }
        JwtUser jwtUser = new JwtUser((String) data.get("username"), token, authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                jwtUser.getUsername(), token, authorities);
        return usernamePasswordAuthenticationToken;
    }
}
