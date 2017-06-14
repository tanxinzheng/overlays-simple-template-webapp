package com.xmomen.module.core.controller;

import com.xmomen.module.test.BaseTestController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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
                .contentType(MediaType.APPLICATION_JSON)
                .param("username", "tanxinzheng")
                .param("password", "111111")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}