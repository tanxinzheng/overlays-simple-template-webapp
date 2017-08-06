package com.xmomen.module.authorization.controller;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.test.BaseTestController;
import com.xmomen.module.authorization.model.UserModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.text.MessageFormat;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author  tanxinzheng
 * @date    2017-6-16 22:59:53
 * @version 1.0.0
 */
public class UserControllerTest extends BaseTestController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static String restMapping = "/user";
    private UserModel userModel;
    private UserModel resultModel;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
        if(userModel == null){
            userModel = new UserModel();
        }
        userModel.setId(TEST_DATA_STRING);
        userModel.setUsername(TEST_DATA_STRING);
        userModel.setNickname(TEST_DATA_STRING);
        userModel.setSalt(TEST_DATA_STRING);
        userModel.setPassword(TEST_DATA_STRING);
        userModel.setEmail(TEST_DATA_STRING);
        userModel.setPhoneNumber(TEST_DATA_STRING);
        userModel.setLocked(TEST_DATA_BOOLEAN);
    }

    @Test
    public void testCreateUser() throws Exception {
        String params = JSONObject.toJSONString(userModel);
        ResultActions actions = mockMvc.perform(post(restMapping)
                .contentType(MediaType.APPLICATION_JSON)
                .content(params)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        UserModel result = JSONObject.parseObject(resultJson, UserModel.class);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getUsername());
        Assert.assertNotNull(result.getNickname());
        Assert.assertNotNull(result.getSalt());
        Assert.assertNotNull(result.getPassword());
        Assert.assertNotNull(result.getEmail());
        Assert.assertNotNull(result.getPhoneNumber());
        Assert.assertNotNull(result.getLocked());
        Assert.assertNotNull(result.getCreatedTime());
        Assert.assertNotNull(result.getLastLoginTime());
        resultModel = result;
    }

    @Test
    public void testGetUserById() throws Exception {
        testCreateUser();
        ResultActions actions = mockMvc.perform(get(MessageFormat.format("{0}/{1}",
                restMapping, resultModel.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        UserModel result = JSONObject.parseObject(resultJson, UserModel.class);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getUsername());
        Assert.assertNotNull(result.getNickname());
        Assert.assertNotNull(result.getSalt());
        Assert.assertNotNull(result.getPassword());
        Assert.assertNotNull(result.getEmail());
        Assert.assertNotNull(result.getPhoneNumber());
        Assert.assertNotNull(result.getLocked());
        Assert.assertNotNull(result.getCreatedTime());
        Assert.assertNotNull(result.getLastLoginTime());
    }

    @Test
    public void testGetUserList() throws Exception {
        testCreateUser();
        // 列表查询
        ResultActions actions = mockMvc.perform(get(restMapping)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        HashMap result = JSONObject.parseObject(resultJson, HashMap.class);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.get(DEFAULT_DATA));
        // 分页查询
        ResultActions pagingActions = mockMvc.perform(get(restMapping)
                .contentType(MediaType.APPLICATION_JSON)
                .param(Page.PARAMETER_PAGE_SIZE, String.valueOf(DEFAULT_PAGE_SIZE))
                .param(Page.PARAMETER_PAGE_NUM, String.valueOf(DEFAULT_PAGE_NUM))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultPageJson = pagingActions.andReturn().getResponse().getContentAsString();
        HashMap resultPage = JSONObject.parseObject(resultPageJson, HashMap.class);
        Assert.assertNotNull(resultPage);
        Assert.assertNotNull(resultPage.get(DEFAULT_DATA));
        Assert.assertNotNull(resultPage.get(DEFAULT_PAGE_INFO));
    }

    @Test
    public void testUpdateUser() throws Exception {
        testCreateUser();
        resultModel.setUsername(TEST_DATA_STRING_UPDATE);
        resultModel.setNickname(TEST_DATA_STRING_UPDATE);
        resultModel.setSalt(TEST_DATA_STRING_UPDATE);
        resultModel.setPassword(TEST_DATA_STRING_UPDATE);
        resultModel.setEmail(TEST_DATA_STRING_UPDATE);
        resultModel.setPhoneNumber(TEST_DATA_STRING_UPDATE);
        resultModel.setLocked(TEST_DATA_BOOLEAN_UPDATE);
        ResultActions actions = mockMvc.perform(put(MessageFormat.format("{0}/{1}",
                restMapping, resultModel.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(resultModel))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        UserModel result = JSONObject.parseObject(resultJson, UserModel.class);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getUsername());
        Assert.assertNotNull(result.getNickname());
        Assert.assertNotNull(result.getSalt());
        Assert.assertNotNull(result.getPassword());
        Assert.assertNotNull(result.getEmail());
        Assert.assertNotNull(result.getPhoneNumber());
        Assert.assertNotNull(result.getLocked());
        Assert.assertNotNull(result.getCreatedTime());
        Assert.assertNotNull(result.getLastLoginTime());
    }

    @Test
    public void testDeleteUser() throws Exception {
        testCreateUser();
        ResultActions actions = mockMvc.perform(delete(MessageFormat.format("{0}/{1}",
                restMapping, resultModel.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUsers() throws Exception {
        testCreateUser();
        ResultActions actions = mockMvc.perform(delete(restMapping)
                .contentType(MediaType.APPLICATION_JSON)
                .param(DEFAULT_BATCH_ID, resultModel.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}