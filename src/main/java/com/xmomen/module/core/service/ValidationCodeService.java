package com.xmomen.module.core.service;

/**
 * Created by tanxinzheng on 17/8/7.
 */
public interface ValidationCodeService {

    /**
     * 发送验证码
     * @param receiver
     */
    void sendCode(String receiver);

    /**
     * 校验验证码
     * @param receiver
     * @param code
     * @return
     */
    boolean validateCode(String receiver, String code);

    /**
     * 清除验证码
     * @param receiver
     */
    void cleanCode(String receiver);

}
