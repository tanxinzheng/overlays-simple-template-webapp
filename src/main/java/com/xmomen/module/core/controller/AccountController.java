package com.xmomen.module.core.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
@Api(value = "当前用户相关信息", description = "当前用户简要信息，权限等相关接口")
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * 查询当前用户简要信息
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户简要信息")
    public AccountModel accountSetting(){
        Subject subject = SecurityUtils.getSubject();
        if(subject == null){
            return null;
        }
        String username = subject.getPrincipal().toString();
        AccountModel accountModel = accountService.getAccountModelByUsername(username);
        return accountModel;
    }

    /**
     * 当前用户权限
     * @return
     */
    @RequestMapping(value = "/account/permissions", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户权限")
    public Map getAccountPermission(){
        Set<String> roles = accountService.findRoles();
        Set<String> permissions = accountService.findPermissions();
        Map rolesMap = new HashMap();
        rolesMap.put("roles", roles);
        rolesMap.put("permissions", permissions);
        return rolesMap;
    }


}
