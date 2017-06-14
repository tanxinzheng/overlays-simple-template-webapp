package com.xmomen.module.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by tanxinzheng on 17/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:config/spring-core.xml",
        "classpath:config/spring-servlet.xml"
})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class BaseTestController {

    public static int DEFAULT_PAGE_SIZE = 10;
    public static int DEFAULT_PAGE_NUM = 1;

    public static String DEFAULT_BATCH_ID = "ids";
    public static String DEFAULT_DATA = "data";
    public static String DEFAULT_PAGE_INFO = "pageInfo";
    public static String TEST_DATA_STRING = "TEST";
    public static Boolean TEST_DATA_BOOLEAN = false;
    public static Integer TEST_DATA_INTEGER = 1;
    public static BigDecimal TEST_DATA_BIG_DECIMAL = BigDecimal.valueOf(1999.99);

    public static String TEST_DATA_STRING_UPDATE = "TEST_UPDATED";
    public static Boolean TEST_DATA_BOOLEAN_UPDATE = true;
    public static Integer TEST_DATA_INTEGER_UPDATE = 2;
    public static BigDecimal TEST_DATA_BIG_DECIMAL_UPDATE = BigDecimal.valueOf(2111.88);

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    public HttpSession getLoginSession() throws Exception {
        ResultActions actions = mockMvc.perform(get("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("username", "admin")
                .param("password", "123456")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        return actions.andReturn().getRequest().getSession();
    }
}
