package com.xmomen.module.security;

import com.xmomen.module.CacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountLockedException;
import java.text.MessageFormat;

/**
 * Created by tanxinzheng on 18/2/12.
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    /**
     * 系统配置可登录失败最大次数
     */
    public static int FAILURE_LOGIN_DEFAULT_MAX_NUMBER = 5;

    /**
     * 重试所需等待时间（分钟）
     */
    public static int FAILURE_LOGIN_WAITING_MINUTE = 15;

    @Autowired
    CacheManager cacheManager;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        String username = authenticationFailureBadCredentialsEvent.getAuthentication().getPrincipal().toString();
        Integer integer = cacheManager.getCache(CacheConfig.FAILURE_LOGIN_MAX_NUMBER_KEY).get(username, Integer.class);
        if(integer != null && integer > FAILURE_LOGIN_DEFAULT_MAX_NUMBER){
            throw new LockedException(MessageFormat.format("已超过最大失败次数，请{0}分钟之后重试。", FAILURE_LOGIN_WAITING_MINUTE));
        }else{
            if(integer == null){
                integer = 0;
            }
            integer++;
            cacheManager.getCache(CacheConfig.FAILURE_LOGIN_MAX_NUMBER_KEY).put(username, integer);
        }
    }
}
