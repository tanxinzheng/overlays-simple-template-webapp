package com.xmomen.module.core.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.xmomen.framework.utils.UUIDGenerator;
import com.xmomen.framework.validator.PhoneValidator;
import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.shiro.PasswordHelper;
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

import static com.xmomen.module.core.controller.ValidationCodeController.VALIDATE_CODE_CACHE_NAME;

/**
 * 重置密码控制器
 */
@RestController
public class ResetPasswordController {

    private static Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

    @Autowired
    UserService userService;

    @Autowired
    CacheManager cacheManager;

    public static final int FIND_TYPE_PHONE = 1;
    public static final int FIND_TYPE_EMAIL = 2;


    /**
     * 找回密码
     * @param type      类型：1-手机，2-邮箱
     * @param receiver  手机号码或邮箱
     * @param password  新密码
     * @param code      验证码
     * @return
     */
    @RequestMapping(value = "/find_password", method = RequestMethod.PUT)
    @ApiOperation(value = "找回密码")
    public void findPassword(@RequestParam(value = "type") Integer type,
                                  @RequestParam(value = "receiver") String receiver,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "code") String code) {
        Assert.isTrue(type.equals(FIND_TYPE_EMAIL) || type.equals(FIND_TYPE_PHONE), "找回方式仅支持：1-邮箱找回，2-手机找回");
        Cache cache = cacheManager.getCache(VALIDATE_CODE_CACHE_NAME);
        String cacheCode = cache.get(receiver, String.class);
        Assert.notNull(cacheCode);
        Assert.isTrue(cacheCode.equals(code), "请输入有效的验证码");
        UserModel userModel = null;
        if(type.equals(FIND_TYPE_EMAIL)){
            Assert.isTrue(EmailValidator.getInstance().isValid(receiver), "请输入正确格式的邮箱");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.notNull(userModel, "该邮箱未注册");
        }else if(type.equals(FIND_TYPE_PHONE)){
            Assert.isTrue(PhoneValidator.getInstance().isValid(receiver), "请输入正确格式的手机号码");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.notNull(userModel, "该手机号码未注册");
        }
        String newSalt = UUIDGenerator.getInstance().getUUID();
        String newPassword = PasswordHelper.encryptPassword(password, newSalt);
        User user = new User();
        user.setSalt(newSalt);
        user.setPassword(newPassword);
        user.setId(userModel.getId());
        userService.updateUser(user);
        cache.put(receiver, null);
    }



}
