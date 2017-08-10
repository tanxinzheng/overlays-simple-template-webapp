package com.xmomen.module.core.service.impl;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.module.core.controller.ValidationCodeController;
import com.xmomen.module.core.service.ValidationCodeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.text.MessageFormat;

/**
 * Created by tanxinzheng on 17/8/7.
 */
@Service
public class ValidationCodeServiceImpl implements ValidationCodeService {

    private static Logger logger = LoggerFactory.getLogger(ValidationCodeServiceImpl.class);

    public static final String VALIDATE_CODE_CACHE_NAME = "validate_code_cache";

    @Autowired
    CacheManager cacheManager;

    /**
     * 发送代码
     *
     * @param receiver
     */
    @Override
    public void sendCode(String receiver) {
        Cache cache = cacheManager.getCache(VALIDATE_CODE_CACHE_NAME);
        String code = RandomStringUtils.randomNumeric(6);
        logger.debug(MessageFormat.format("The validation code stored to cache , Receiver Key: {0}, Cache Key: {1} ", receiver, code));
        cache.put(receiver, code);
    }

    /**
     * 校验代码
     *
     * @param receiver
     * @param code
     * @return
     */
    @Override
    public boolean validateCode(String receiver, String code) {
        try {
            Cache cache = cacheManager.getCache(VALIDATE_CODE_CACHE_NAME);
            Assert.notNull(cache);
            String cacheCode = cache.get(receiver, String.class);
            Assert.notNull(cacheCode);
            Assert.isTrue(cacheCode.equals(code));
            return true;
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new BusinessException("请输入有效的验证码");
        }
    }

    /**
     * 清除验证码
     *
     * @param receiver
     */
    @Override
    public void cleanCode(String receiver) {
        Cache cache = cacheManager.getCache(VALIDATE_CODE_CACHE_NAME);
        if(cache == null){
            return;
        }
        cache.put(receiver, null);
    }
}
