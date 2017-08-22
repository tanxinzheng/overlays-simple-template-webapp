package com.xmomen.module.security;

import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
    @Value("${jwt.expire-time}")
    private Long expireTime;
    @Autowired
    AccountService accountService;
    @Autowired
    CacheManager cacheManager;

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
        System.out.println(new Date());
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("account", accountModel);
        claims.put("permissions", permissions.stream().map(s -> s.toString()).collect(Collectors.toList()));
        claims.put("roles", roles.stream().map(s -> s.toString()).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setClaims(claims)
//                .setExpiration(new Date(new Date().getTime() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        Cache cache = cacheManager.getCache(JWT_TOKEN_SESSION_KEY);
        cache.put(token, claims);
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
            Claims claims = cache.get(token, Claims.class);
            if(claims == null){
                // session会话超时
                return false;
            }
            // 延长session会话时间
            cache.put(token, claims);
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
        Claims claims = jwt.getBody();
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        List<String> permissions = (List<String>) claims.get("permissions");
        if(!CollectionUtils.isEmpty(permissions)){
            for (String permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        }
        JwtUser jwtUser = new JwtUser(claims.getSubject(), token, authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                jwtUser.getUsername(), token, authorities);
        return usernamePasswordAuthenticationToken;
    }
}
