package com.xmomen.module.security;

import com.google.common.collect.Maps;
import com.xmomen.framework.utils.UUIDGenerator;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
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
@Slf4j
public class JwtTokenServiceImpl implements JwtTokenService {

    public static final String HEADER_AUTHORIZATION_NAME = "Authorization";

    public static final String JWT_TOKEN_SESSION_KEY = "jwt-token-cache";

    public static final String JWT_TOKEN_COOKIE_KEY = "jwt-token";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;
    @Autowired
    AccountService accountService;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    RedisTemplate redisTemplate;

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
        if(StringUtils.isNotBlank(token)){
            token = token.replaceAll("Bearer ", "");
        }
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
     * 刷新token
     *
     * @param request
     */
    @Override
    public void refreshToken(HttpServletRequest request) {

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
        String token = createToken(username);
        RedisCache cache = new RedisCache(JWT_TOKEN_SESSION_KEY, null, redisTemplate, 10, false);
        cache.put(token, data);
        response.addHeader(HEADER_AUTHORIZATION_NAME, "Bearer " + token);
        Cookie cookie = new Cookie(JwtTokenServiceImpl.JWT_TOKEN_COOKIE_KEY, token);
        cookie.setSecure(Boolean.TRUE);
        response.addCookie(cookie);
    }

    private String createToken(String username){

        Claims claims = Jwts.claims()
                .setId(String.valueOf(UUIDGenerator.getInstance().getUUID()))
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(expiration) * 1000))
                .setIssuedAt(new Date());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
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
            try {
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            } catch (Exception e){
                log.error(e.getMessage(), e);
                return false;
            }
            RedisCache cache = (RedisCache) cacheManager.getCache(JWT_TOKEN_SESSION_KEY);
            HashMap data = cache.get(token, HashMap.class);
            if(data == null){
                // session会话超时
                return false;
            }
            // 延长session会话时间
            cache.put(token, data);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
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
    public String getUsername(String token) {
        Jws<Claims> data = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return data.getBody().getSubject();
    }

    @Override
    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        return getAuthentication(token);
    }

    @Override
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
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
        String username = (String) data.get("username");
        JwtUser jwtUser = new JwtUser(username, token, authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                jwtUser.getUsername(), token, authorities);
        AccountModel accountModel = accountService.getAccountModelByUsername(username);
        usernamePasswordAuthenticationToken.setDetails(accountModel);
        return usernamePasswordAuthenticationToken;
    }
}
