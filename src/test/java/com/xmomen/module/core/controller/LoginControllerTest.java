package com.xmomen.module.core.controller;

import com.xmomen.module.security.JwtTokenServiceImpl;
import com.xmomen.module.test.BaseTestController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.springframework.boot.test.autoconfigure.web.servlet.W;

/**
 * Created by tanxinzheng on 17/6/12.
 */
public class LoginControllerTest extends BaseTestController {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() throws Exception {
        ResultActions loginActions = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("username", "admin")
                .param("password", "111111")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        String token = loginActions.andReturn().getResponse().getHeader(JwtTokenServiceImpl.HEADER_AUTHORIZATION_NAME);
        Claims claims = Jwts.parser().parseClaimsJws(token).getBody();
        Date expiration = claims.getExpiration();
        System.out.println(DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(expiration));
    }

}