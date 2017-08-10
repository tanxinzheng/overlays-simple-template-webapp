package com.xmomen.module.shiro.realm;

import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 用户授权领域对象
 */
public class UserRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(UserRealm.class);

    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        AccountModel accountModel = accountService.getSessionModel();
        authorizationInfo.setRoles(accountService.findRoles(accountModel.getUserId()));
        authorizationInfo.setStringPermissions(accountService.findPermissions(accountModel.getUserId()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        SimpleAccount account;
        try {
            account = accountService.getAccountByUsername(username);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
            throw new AuthenticationException(ex);
        }
        if(account == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if(account.isLocked()) {
            throw new LockedAccountException(); //帐号锁定
        }
        return account;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
