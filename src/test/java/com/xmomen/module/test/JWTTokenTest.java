package com.xmomen.module.test;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.security.Key;
import java.util.UUID;

/**
 * Created by tanxinzheng on 17/6/12.
 */
public class JWTTokenTest {

    @Test
    public void test() throws Exception{
        String username = "admin";
        Key key = MacProvider.generateKey();
        String compactJws = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println(compactJws);
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);
            //OK, we can trust this JWT
        } catch (SignatureException e) {
            //don't trust the JWT!
        }
    }

    @Test
    public void test2() throws Exception{
        System.out.println(UUID.randomUUID().toString());

        System.out.println(RandomStringUtils.randomNumeric(32));
        System.out.println(RandomStringUtils.randomAlphabetic(32));
        System.out.println(RandomStringUtils.randomAscii(32));
    }
}
