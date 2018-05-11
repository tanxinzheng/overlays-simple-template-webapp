package com.xmomen.module.security;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tanxinzheng on 18/2/23.
 */
public class JwtTokenServiceImplTest {

    @Test
    public void createToken() throws Exception {
        JwtTokenServiceImpl jwtTokenService = new JwtTokenServiceImpl();
        String token = jwtTokenService.createToken("admin", "xmomen");
        System.out.println(token);
    }

}