package com.xmomen.module.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.module.core.model.DemoModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by tanxinzheng on 16/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring-core.xml","classpath:config/spring-servlet.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class DemoControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testTest1() throws Exception {
        Date date = new Date();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "测试人员");
        jsonObject.put("age", 18);
        jsonObject.put("createTime", "20161011");
        jsonObject.put("updateTime", new Date().getTime());
//        jsonObject.put("createTime", 42715.122650463);
        jsonObject.put("amount", 100.4322);
        ResultActions actions = mockMvc.perform(post("/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toJSONString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        DemoModel result = JSONObject.parseObject(resultJson, DemoModel.class);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getAge());
        Assert.assertNotNull(result.getAmount());
        Assert.assertNotNull(result.getCreateTime());
        Assert.assertNotNull(result.getUpdateTime());
        Assert.assertNotNull(result.getName());
    }
}