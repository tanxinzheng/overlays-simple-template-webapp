package com.xmomen.module.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.module.test.BaseTestController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 17/6/27.
 */
public class CacheControllerTest extends BaseTestController {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDictionaryList() throws Exception {
        // 列表查询
        ResultActions actions = mockMvc.perform(get("/select/cache")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        HashMap result = JSONObject.parseObject(resultJson, HashMap.class);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.get(DEFAULT_DATA));
    }

}