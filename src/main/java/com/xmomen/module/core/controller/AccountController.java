package com.xmomen.module.core.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.utils.UUIDGenerator;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import com.xmomen.module.shiro.PasswordHelper;
import io.jsonwebtoken.lang.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
@Api(value = "当前用户相关信息", description = "当前用户简要信息，权限等相关接口")
public class AccountController extends BaseRestController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    /**
     * 查询当前用户简要信息
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
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
    @ActionLog(actionName = "查询当前用户权限")
    public Map getAccountPermission(){
        Set<String> roles = accountService.findRoles();
        Set<String> permissions = accountService.findPermissions();
        Map rolesMap = new HashMap();
        rolesMap.put("roles", roles);
        rolesMap.put("permissions", permissions);
        return rolesMap;
    }

    /**
     * 当前用户修改密码
     * @param oldPassword
     * @param password
     */
    @RequestMapping(value = "/account/password", method = RequestMethod.PUT)
    @ApiOperation(value = "当前用户修改密码")
    @ActionLog(actionName = "当前用户修改密码")
    public void updatePassword(@RequestParam(value = "oldPassword") String oldPassword,
                              @RequestParam(value = "password") String password){
        AccountModel accountModel = getCurrentAccount();
        UserModel userModel = userService.getOneUserModel(accountModel.getUserId());
        Assert.notNull(userModel, "未找到当前用户帐号");
        String encryptPassword = PasswordHelper.encryptPassword(oldPassword, userModel.getSalt());
        Assert.isTrue(encryptPassword.equals(userModel.getPassword()), " 密码不正确");
        String salt = UUIDGenerator.getInstance().getUUID();
        String newEncryptPassword = PasswordHelper.encryptPassword(password, userModel.getSalt());
    }

}
