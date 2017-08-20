package com.xmomen.module.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
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

/**
 * Created by tanxinzheng on 17/8/18.
 */
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    public static final String HEADER_AUTHORIZATION_NAME = "Authorization";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expire-time}")
    private Long expireTime;

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
        System.out.println(new Date());
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(new Date().getTime() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        System.out.println(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration());
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
            System.out.println(jwt.getBody().getExpiration());
            return true;
        } catch (Exception e) {
            throw new JwtException(e.getMessage(), e);
        }
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        Jws<Claims> jwt = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        Claims claims = jwt.getBody();
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        List<String> roles = (List<String>) claims.get("Authorization");
        if(!CollectionUtils.isEmpty(roles)){
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        JwtUser jwtUser = new JwtUser(claims.getSubject(), token, authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                jwtUser.getUsername(), token, authorities);
        return usernamePasswordAuthenticationToken;
    }
}
