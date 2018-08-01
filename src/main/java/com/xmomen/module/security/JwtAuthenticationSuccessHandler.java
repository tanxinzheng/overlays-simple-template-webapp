package com.xmomen.module.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanxinzheng on 17/8/20.
 */
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;

    public JwtAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = response.getHeader(JwtTokenServiceImpl.HEADER_AUTHORIZATION_NAME);
        User user = new User();
        user.setUsername(String.valueOf(authentication.getPrincipal()));
        user.setLastLoginTime(new Date());
        userService.updateUser(user);
        Map<String, Object> data = new HashMap<>();
        data.put("username", authentication.getPrincipal());
        data.put("token", token);
        data.put("refreshToken", token);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, data);
        out.flush();
    }

}
