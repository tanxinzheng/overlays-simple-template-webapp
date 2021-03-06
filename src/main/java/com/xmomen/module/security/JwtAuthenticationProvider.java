package com.xmomen.module.security;

import com.xmomen.module.CacheConfig;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.core.service.AccountService;
import com.xmomen.framework.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

import static com.xmomen.module.security.AuthenticationFailureListener.FAILURE_LOGIN_DEFAULT_MAX_NUMBER;

/**
 * Created by tanxinzheng on 17/8/19.
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    CacheManager cacheManager;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        Integer maxNum = cacheManager.getCache(CacheConfig.FAILURE_LOGIN_MAX_NUMBER_KEY).get(username, Integer.class);
        if(maxNum != null && maxNum > FAILURE_LOGIN_DEFAULT_MAX_NUMBER){
            throw new LockedException("该帐号已被锁，请联系平台管理员");
        }
        UserModel userModel = userService.getOneUserModelByUsername(username);
        if(userModel == null){
            throw new BadCredentialsException("用户名或密码错误");
        }
        if(userModel.getLocked() != null && userModel.getLocked()){
            throw new LockedException("该帐号已被锁，请联系平台管理员");
        }
        if(!PasswordHelper.encryptPassword(password, userModel.getSalt()).equals(userModel.getPassword())){
            throw new BadCredentialsException("用户名或密码错误");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,
                userModel.getPassword(),
                accountService.findPermissions(userModel.getId()).stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList()));
        usernamePasswordAuthenticationToken.setDetails(userModel);
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
