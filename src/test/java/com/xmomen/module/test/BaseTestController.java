package com.xmomen.module.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by tanxinzheng on 17/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring-core.xml","classpath:config/spring-servlet.xml"})
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
}
