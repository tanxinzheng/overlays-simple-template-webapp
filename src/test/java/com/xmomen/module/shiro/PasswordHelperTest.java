package com.xmomen.module.shiro;

import com.xmomen.framework.utils.PasswordHelper;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;

/**
 * Created by tanxinzheng on 18/2/9.
 */
public class PasswordHelperTest {


    @Test
    public void encryptPassword() throws Exception {
        String password = PasswordHelper.encryptPassword("111111", "d4cb830ca964470f9d3866ae5e9a0c7b");
        Assert.isTrue(password.equals("f5c4e1c3cb0c854e8b8623d7a61432d3"));
    }

}