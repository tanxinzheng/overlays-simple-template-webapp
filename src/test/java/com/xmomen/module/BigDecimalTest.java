package com.xmomen.module;

import org.junit.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * Created by tanxinzheng on 16/12/21.
 */
public class BigDecimalTest {

    @Test
    public void testEncryptPassword() throws Exception {
        int len = 8;
        int scale = 4;
        BigDecimal max = new BigDecimal(Math.pow(10, len+1)).subtract(BigDecimal.valueOf(1/(Math.pow(10, scale)))).setScale(scale);
        Assert.isTrue(max.compareTo(new BigDecimal(999999999.9999).setScale(scale)) == 0);
    }
}
