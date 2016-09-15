package com.xmomen.framework.generator.service;

import com.xmomen.framework.generator.model.TableDefine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tanxinzheng on 16/9/15.
 */
public class CodeGenerateServiceTest {

    ApplicationContext dsContext = null;
    CodeGenerateService generateService;

    @Before
    public void setUp() throws Exception {
        dsContext = new ClassPathXmlApplicationContext(new String[] {
                "config/spring-core.xml"
        });
        generateService = dsContext.getBean(CodeGenerateService.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetAllTableDefine() throws Exception {
        List<TableDefine> tableDefineList = generateService.getAllTableDefine();
        Assert.assertNotNull(tableDefineList);
        Assert.assertTrue(tableDefineList.size() > 0);
    }
}