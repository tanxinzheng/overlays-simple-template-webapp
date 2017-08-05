package com.xmomen.module.core.controller;

import com.xmomen.framework.validator.PhoneValidator;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

import static com.xmomen.module.core.controller.ResetPasswordController.FIND_TYPE_EMAIL;
import static com.xmomen.module.core.controller.ResetPasswordController.FIND_TYPE_PHONE;

/**
 * Created by tanxinzheng on 17/8/4.
 */
@RestController
public class ValidationCodeController {

    private static Logger logger = LoggerFactory.getLogger(ValidationCodeController.class);

    public static final String VALIDATE_CODE_CACHE_NAME = "validate_code_cache";

    @Autowired
    CacheManager cacheManager;

    @Autowired
    UserService userService;

    /**
     * 发送验证码
     * @param type      类型：1-手机，2-邮箱
     * @param receiver  手机号码或邮箱
     */
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    public void setValidateCode(@RequestParam(value = "type") Integer type,
                                @RequestParam(value = "receiver") String receiver){
        Assert.isTrue(type.equals(FIND_TYPE_EMAIL) ||
                type.equals(FIND_TYPE_PHONE), "找回方式仅支持：1-邮箱找回，2-手机找回");
        UserModel userModel = null;
        if(type.equals(FIND_TYPE_PHONE)){
            Assert.isTrue(PhoneValidator.getInstance().isValid(receiver), "请输入正确格式的手机号码");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.notNull(userModel, "该手机号码未注册");
        }else if(type.equals(FIND_TYPE_EMAIL)){
            Assert.isTrue(EmailValidator.getInstance().isValid(receiver), "请输入正确格式的邮箱");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.notNull(userModel, "该邮箱未注册");
        }
        Cache cache = cacheManager.getCache(VALIDATE_CODE_CACHE_NAME);
        String code = RandomStringUtils.randomNumeric(6);
        logger.debug(MessageFormat.format("The validation code stored to cache , Cache Type: {0}, Cache Key: {1} ", type, code));
        cache.put(receiver, code);
    }
}
