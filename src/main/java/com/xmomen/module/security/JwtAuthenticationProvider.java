package com.xmomen.module.security;

import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.core.service.AccountService;
import com.xmomen.module.shiro.PasswordHelper;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by tanxinzheng on 17/8/19.
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserModel userModel = userService.getOneUserModelByUsername(username);
        if(userModel == null){
            throw new BadCredentialsException("用户名或密码错误");
        }
        if(userModel.getLocked()){
            throw new BadCredentialsException("该帐号已被锁，请联系平台管理员");
        }
        if(!PasswordHelper.encryptPassword(password, userModel.getSalt()).equals(userModel.getPassword())){
            throw new BadCredentialsException("用户名或密码错误");
        }
        return new UsernamePasswordAuthenticationToken(username,
                userModel.getPassword(),
                Collections.arrayToList(accountService.findRoles(username).toArray()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
