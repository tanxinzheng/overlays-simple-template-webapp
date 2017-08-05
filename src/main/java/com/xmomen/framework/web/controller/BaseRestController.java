package com.xmomen.framework.web.controller;

import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 17/6/12.
 */
@RestController
public class BaseRestController {

    @Autowired
    AccountService accountService;

    /**
     * 获取当前用户ID
     * @return
     */
    public String getCurrentUserId(){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            throw new AuthenticationException();
        }
        String username = subject.getPrincipal().toString();
        AccountModel accountModel = accountService.getAccountModelByUsername(username);
        return accountModel.getUserId();
    }

    /**
     * 获取当前用户
     * @return
     */
    public AccountModel getCurrentAccount() {
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            throw new AuthenticationException();
        }
        String username = subject.getPrincipal().toString();
        return accountService.getAccountModelByUsername(username);
    }
}
