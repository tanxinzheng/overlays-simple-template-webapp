package com.xmomen.framework.web.controller;

import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 17/6/12.
 */
@RestController
public class BaseRestController {

    @Autowired
    private AccountService accountService;

    /**
     * 获取当前用户ID
     * @return
     */
    public String getCurrentUserId(){
        Authentication subject = SecurityContextHolder.getContext().getAuthentication();
        if(!subject.isAuthenticated()){
            throw new AccessDeniedException("权限不足");
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
        Authentication subject = SecurityContextHolder.getContext().getAuthentication();
        if(!subject.isAuthenticated()){
            throw new AccessDeniedException("权限不足");
        }
        String username = subject.getPrincipal().toString();
        return accountService.getAccountModelByUsername(username);
    }
}
