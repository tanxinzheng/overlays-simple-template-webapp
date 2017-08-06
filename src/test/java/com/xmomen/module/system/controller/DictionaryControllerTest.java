package com.xmomen.module.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.test.BaseTestController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.text.MessageFormat;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 10:11:18
 * @version 1.0.0
 */
public class DictionaryControllerTest extends BaseTestController {



    private static String restMapping = "/dictionary";
    private DictionaryModel dictionaryModel;
    private DictionaryModel resultModel;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        if(dictionaryModel == null){
            dictionaryModel = new DictionaryModel();
        }
        dictionaryModel.setId(TEST_DATA_STRING);
        dictionaryModel.setGroupName(TEST_DATA_STRING);
        dictionaryModel.setGroupCode(TEST_DATA_STRING);
        dictionaryModel.setDictionaryName(TEST_DATA_STRING);
        dictionaryModel.setDictionaryCode(TEST_DATA_STRING);
        dictionaryModel.setSort(TEST_DATA_INTEGER);
        dictionaryModel.setActive(TEST_DATA_BOOLEAN);
        dictionaryModel.setParentId(TEST_DATA_STRING);
        dictionaryModel.setIsShow(TEST_DATA_BOOLEAN);
    }

    @Test
    public void testCreateDictionary() throws Exception {
        String params = JSONObject.toJSONString(dictionaryModel);
        ResultActions actions = mockMvc.perform(post(restMapping)
                .contentType(MediaType.APPLICATION_JSON)
                .content(params)
                .session((MockHttpSession)getLoginSession())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        DictionaryModel result = JSONObject.parseObject(resultJson, DictionaryModel.class);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getGroupName());
        Assert.assertNotNull(result.getGroupCode());
        Assert.assertNotNull(result.getDictionaryName());
        Assert.assertNotNull(result.getDictionaryCode());
        Assert.assertNotNull(result.getSort());
        Assert.assertNotNull(result.getActive());
        Assert.assertNotNull(result.getParentId());
        Assert.assertNotNull(result.getIsShow());
        resultModel = result;
    }

    @Test
    public void testGetDictionaryById() throws Exception {
        testCreateDictionary();
        ResultActions actions = mockMvc.perform(get(MessageFormat.format("{0}/{1}",
                restMapping, resultModel.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        DictionaryModel result = JSONObject.parseObject(resultJson, DictionaryModel.class);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getGroupName());
        Assert.assertNotNull(result.getGroupCode());
        Assert.assertNotNull(result.getDictionaryName());
        Assert.assertNotNull(result.getDictionaryCode());
        Assert.assertNotNull(result.getSort());
        Assert.assertNotNull(result.getActive());
        Assert.assertNotNull(result.getParentId());
        Assert.assertNotNull(result.getIsShow());
    }

    @Test
    public void testGetDictionaryList() throws Exception {
        testCreateDictionary();
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
    public void testUpdateDictionary() throws Exception {
        testCreateDictionary();
        resultModel.setGroupName(TEST_DATA_STRING_UPDATE);
        resultModel.setGroupCode(TEST_DATA_STRING_UPDATE);
        resultModel.setDictionaryName(TEST_DATA_STRING_UPDATE);
        resultModel.setDictionaryCode(TEST_DATA_STRING_UPDATE);
        resultModel.setSort(TEST_DATA_INTEGER_UPDATE);
        resultModel.setActive(TEST_DATA_BOOLEAN_UPDATE);
        resultModel.setParentId(TEST_DATA_STRING_UPDATE);
        resultModel.setIsShow(TEST_DATA_BOOLEAN_UPDATE);
        resultModel.setIsShow(false);
        resultModel.setActive(false);
        ResultActions actions = mockMvc.perform(put(MessageFormat.format("{0}/{1}",
                restMapping, resultModel.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(resultModel))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        DictionaryModel result = JSONObject.parseObject(resultJson, DictionaryModel.class);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getGroupName());
        Assert.assertNotNull(result.getGroupCode());
        Assert.assertNotNull(result.getDictionaryName());
        Assert.assertNotNull(result.getDictionaryCode());
        Assert.assertNotNull(result.getSort());
        Assert.assertNotNull(result.getActive());
        Assert.assertNotNull(result.getParentId());
        Assert.assertNotNull(result.getIsShow());
    }

    @Test
    public void testDeleteDictionary() throws Exception {
        testCreateDictionary();
        ResultActions actions = mockMvc.perform(delete(MessageFormat.format("{0}/{1}",
                restMapping, resultModel.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDictionarys() throws Exception {
        testCreateDictionary();
        ResultActions actions = mockMvc.perform(delete(restMapping)
                .contentType(MediaType.APPLICATION_JSON)
                .param(DEFAULT_BATCH_ID, resultModel.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}