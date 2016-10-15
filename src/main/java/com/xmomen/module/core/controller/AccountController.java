package com.xmomen.module.core.controller;

import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import com.xmomen.module.permission.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
public class AccountController {

    @Autowired
    PermissionService permissionService;

    @Autowired
    AccountService accountService;

    /**
     * 用户设置
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public AccountModel accountSetting(){
        Subject subject = SecurityUtils.getSubject();
        if(subject == null){
            return null;
        }
        String username = subject.getPrincipal().toString();
        AccountModel accountModel = accountService.getAccountByUsername(username);
        //Set<String> roles = permissionService.findRoles(username);

        return accountModel;
    }
//
//    /**
//     * 用户修改密码
//     * @return
//     */
//    @RequestMapping(value = "/account/resetPassword", method = RequestMethod.POST)
//    public void resetPassword(@RequestParam(value = "current_password") String currentPassword,
//                              @RequestParam(value = "password") String password){
//        Subject subject = SecurityUtils.getSubject();
//        String username = subject.getPrincipal().toString();
//        userService.changePassword(username, currentPassword, password);
//    }


}
