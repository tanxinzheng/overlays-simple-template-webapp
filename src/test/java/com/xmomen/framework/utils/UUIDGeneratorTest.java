package com.xmomen.framework.utils;

import io.jsonwebtoken.lang.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tanxinzheng on 17/8/5.
 */
public class UUIDGeneratorTest {
    
    @Test
    public void getUUID() throws Exception {
        Assert.isTrue(UUIDGenerator.getInstance().getUUID().length() == 32);
    }

    @Test
    public void getUUID1() throws Exception {
        int num1 = 64;
        int num2 = 128;
        Assert.isTrue(UUIDGenerator.getInstance().getUUID(num1).length() == num1);
        Assert.isTrue(UUIDGenerator.getInstance().getUUID(num2).length() == num2);
    }

}