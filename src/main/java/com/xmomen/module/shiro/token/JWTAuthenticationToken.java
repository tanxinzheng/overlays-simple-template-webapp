package com.xmomen.module.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * Created by tanxinzheng on 17/6/11.
 */
public class JWTAuthenticationToken implements AuthenticationToken {

    public static final String JWT_TOKEN_KEY = "abcd1234";

    private String username;
    private String token;

    public JWTAuthenticationToken(String username, String token) {
        this.username = username;
        this.token = token;
    }

    /**
     * Returns the account identity submitted during the authentication process.
     * <p/>
     * <p>Most application authentications are username/password based and have this
     * object represent a username.  If this is the case for your application,
     * take a look at the {@link UsernamePasswordToken UsernamePasswordToken}, as it is probably
     * sufficient for your use.
     * <p/>
     * <p>Ultimately, the object returned is application specific and can represent
     * any account identity (user id, X.509 certificate, etc).
     *
     * @return the account identity submitted during the authentication process.
     * @see UsernamePasswordToken
     */
    @Override
    public Object getPrincipal() {
        return username;
    }

    /**
     * Returns the credentials submitted by the user during the authentication process that verifies
     * the submitted {@link #getPrincipal() account identity}.
     * <p/>
     * <p>Most application authentications are username/password based and have this object
     * represent a submitted password.  If this is the case for your application,
     * take a look at the {@link UsernamePasswordToken UsernamePasswordToken}, as it is probably
     * sufficient for your use.
     * <p/>
     * <p>Ultimately, the credentials Object returned is application specific and can represent
     * any credential mechanism.
     *
     * @return the credential submitted by the user during the authentication process.
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}
