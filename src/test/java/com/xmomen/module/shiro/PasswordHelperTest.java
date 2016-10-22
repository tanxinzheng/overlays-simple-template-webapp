package com.xmomen.module.shiro;

import com.xmomen.commons.StringUtilsExt;
import com.xmomen.module.shiro.credentials.RetryLimitHashedCredentialsMatcher;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tanxinzheng on 16/10/13.
 */
public class PasswordHelperTest {

    @Test
    public void testEncryptPassword() throws Exception {
        String salt2= StringUtilsExt.getUUID(32);
        System.out.println(salt2);
        System.out.println(PasswordHelper.encryptPassword("111111", salt2));
    }
}