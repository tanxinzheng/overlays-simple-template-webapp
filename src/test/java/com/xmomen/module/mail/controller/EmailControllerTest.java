package com.xmomen.module.mail.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xmomen.module.mail.model.EmailModel;
import com.xmomen.module.security.JwtTokenServiceImpl;
import com.xmomen.module.test.BaseTestController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2018/6/10.
 */
public class EmailControllerTest extends BaseTestController {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    private static EmailModel buildEmail(){
        EmailModel emailModel = new EmailModel();
        String sendTo = "277303310@qq.com";
        String title = "测试邮件标题";
        String content = "测试邮件内容";
        emailModel.setText(content);
        emailModel.setSubject(title);
        emailModel.setTo(new String[]{sendTo});
        return emailModel;
    }

    /**
     * 发送普通邮件
     * @throws Exception
     */
    @Test
    public void sendEmail() throws Exception {
        EmailModel emailModel = buildEmail();
        mockMvc.perform(post("/email")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(emailModel))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * 发送附件邮件
     * @throws Exception
     */
    @Test
    public void sendAttachmentEmail() throws Exception {
        EmailModel emailModel = buildEmail();
        List<String> list = Lists.newArrayList();
        // 添加文件系统 key
        list.add("b501bbf32ed2407499fc13e8731af6c0");
        emailModel.setAttachmentKeys(list);
        mockMvc.perform(post("/email")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(emailModel))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * 发送定时邮件
     * @throws Exception
     */
    @Test
    public void sendTemplateEmail() throws Exception {
        EmailModel emailModel = buildEmail();
        emailModel.setSubject("测试邮件模板标题：【${username}】");
        emailModel.setText("这是一封定时邮件，发送时间: #{}");
        emailModel.setTemplateCode("M001");
        Map map = Maps.newHashMap();
        map.put("username", "测试人员");
        map.put("sendTime", DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
        emailModel.setTemplateData(map);
        mockMvc.perform(post("/email")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(emailModel))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * 发送邮件非空校验
     * @throws Exception
     */
    @Test
    public void sendValidEmail() throws Exception {
        EmailModel emailModel = buildEmail();
        emailModel.setTo(null);
        mockMvc.perform(post("/email")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(emailModel))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }


}