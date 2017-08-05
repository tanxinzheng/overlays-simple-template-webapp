package com.xmomen.framework.validator;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tanxinzheng on 17/8/5.
 */
public class PhoneValidator implements Serializable {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");

    public static final PhoneValidator PHONE_VALIDATOR = new PhoneValidator();

    public static PhoneValidator getInstance(){
        return PHONE_VALIDATOR;
    }

    /**
     * 验证手机号码格式是否正确
     * @param phone
     * @return
     */
    public boolean isValid(String phone) {
        if(phone == null) {
            return false;
        } else {
            Matcher phoneMatcher = PHONE_PATTERN.matcher(phone);
            return phoneMatcher.matches();
        }
    }
}
