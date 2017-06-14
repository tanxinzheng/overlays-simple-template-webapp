package com.xmomen.module.shiro.realm;

import com.xmomen.module.core.service.AccountService;
import com.xmomen.module.shiro.token.JWTAuthenticationToken;
import io.jsonwebtoken.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by tanxinzheng on 17/6/11.
 */
public class JWTRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(JWTRealm.class);

    AccountService accountService;

    /**
     * Convenience implementation that returns
     * <tt>getAuthenticationTokenClass().isAssignableFrom( token.getClass() );</tt>.  Can be overridden
     * by subclasses for more complex token checking.
     * <p>Most configurations will only need to set a different class via
     * {@link #setAuthenticationTokenClass}, as opposed to overriding this method.
     *
     * @param token the token being submitted for authentication.
     * @return true if this authentication realm can process the submitted token instance of the class, false otherwise.
     */
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof JWTAuthenticationToken;
    }

    /**
     * Retrieves the AuthorizationInfo for the given principals from the underlying data store.  When returning
     * an instance from this method, you might want to consider using an instance of
     * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAccount account = (SimpleAccount) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles((Set<String>) account.getRoles());
        authorizationInfo.setStringPermissions((Set<String>) account.getStringPermissions());
        return authorizationInfo;
    }

    /**
     * Retrieves authentication data from an implementation-specific datasource (RDBMS, LDAP, etc) for the given
     * authentication token.
     * <p/>
     * For most datasources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param token the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) token;
        String username = (String) jwtAuthenticationToken.getPrincipal();
        SimpleAccount accountModel = accountService.getAccountByUsername(username);
        //验证用户以及账套
        if(accountModel != null &&
                validToken(String.valueOf(jwtAuthenticationToken.getCredentials()))) {
            //验证摘要信息
            return new SimpleAccount(
                    accountModel, //用户名
                    token,
                    getName());  //realm name
        }
        return null;
    }

    private boolean validToken(String token){
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(JWTAuthenticationToken.JWT_TOKEN_KEY).parseClaimsJws(token);
            //OK, we can trust this JWT
            return true;
        } catch (SignatureException e) {
            //don't trust the JWT!
            return false;
        }
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
