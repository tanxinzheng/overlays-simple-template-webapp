package com.xmomen.module.security;

import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by tanxinzheng on 17/8/18.
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        setAuthenticationManager(authenticationManager);
        this.jwtTokenService = jwtTokenService;
    }

    private JwtTokenService jwtTokenService;

    @Autowired
    private UserService userService;


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        jwtTokenService.setToken(request, response, (String) authResult.getPrincipal());
//        rememberMeServices.loginSuccess(request, response, auth);
        // Fire event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(
                    authResult, this.getClass()));
        }
        UserModel userModel = (UserModel) authResult.getDetails();
        if(userModel != null){
            User user = new User();
            user.setId(userModel.getId());
            user.setLastLoginTime(new Date());
            userService.updateUser(user);
        }
        getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }


}
