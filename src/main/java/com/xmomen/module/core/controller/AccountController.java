package com.xmomen.module.core.controller;

import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.utils.UUIDGenerator;
import com.xmomen.framework.validator.PhoneValidator;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import com.xmomen.module.core.service.ValidationCodeService;
import com.xmomen.module.notification.model.NotificationDataState;
import com.xmomen.module.notification.model.NotificationModel;
import com.xmomen.module.notification.model.NotificationQuery;
import com.xmomen.module.notification.model.NotificationStateCount;
import com.xmomen.module.notification.service.NotificationReceiveService;
import com.xmomen.module.notification.service.NotificationService;
import com.xmomen.framework.utils.PasswordHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.xmomen.module.core.controller.AccessController.FIND_TYPE_EMAIL;
import static com.xmomen.module.core.controller.AccessController.FIND_TYPE_PHONE;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
@Api(value = "当前用户相关信息", description = "当前用户简要信息，权限等相关接口")
@RequestMapping(value = "/account")
public class AccountController extends BaseRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    ValidationCodeService validationCodeService;
    
    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationReceiveService notificationReceiveService;

    /**
     * 查询当前用户简要信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public AccountModel accountSetting(){
        return getCurrentAccount();
    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public Page<NotificationModel> getNotificationPage(NotificationQuery notificationQuery){
        if(notificationQuery == null){
            notificationQuery = new NotificationQuery();
        }
        notificationQuery.setUserId(getCurrentUserId());
        notificationQuery.setDataState(NotificationDataState.UNREAD.name());
        return notificationReceiveService.selectNotification(notificationQuery);
    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public NotificationModel getNotification(@PathVariable(value = "id") String id){
        return notificationReceiveService.selectOneNotificationModel(id);
    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public List<NotificationStateCount> notificationCount(NotificationQuery notificationQuery){
        if(notificationQuery == null){
            notificationQuery = new NotificationQuery();
        }
        notificationQuery.setUserId(getCurrentUserId());
        return notificationService.countNotificationState(notificationQuery);
    }

    /**
     * 当前用户权限
     * @return
     */
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户权限")
    @ActionLog(actionName = "查询当前用户权限")
    public Map getAccountPermission(){
        String userId = getCurrentUserId();
        Set<String> roles = accountService.findRoles(userId);
        Set<String> permissions = accountService.findPermissions(userId);
        Map rolesMap = new HashMap();
        rolesMap.put("roles", roles);
        rolesMap.put("permissions", permissions);
        return rolesMap;
    }

    /**
     * 当前用户修改密码
     * @param oldPassword
     * @param password
     */
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @ApiOperation(value = "当前用户修改密码")
    @ActionLog(actionName = "当前用户修改密码")
    public void updatePassword(@RequestParam(value = "oldPassword") String oldPassword,
                              @RequestParam(value = "password") String password){
        AccountModel accountModel = getCurrentAccount();
        UserModel userModel = userService.getOneUserModel(accountModel.getUserId());
        Assert.notNull(userModel, "未找到当前用户帐号");
        String encryptPassword = PasswordHelper.encryptPassword(oldPassword, userModel.getSalt());
        Assert.isTrue(encryptPassword.equals(userModel.getPassword()), "输入的旧密码不正确");
        String salt = UUIDGenerator.getInstance().getUUID();
        String newEncryptPassword = PasswordHelper.encryptPassword(password, userModel.getSalt());
        User user = new User();
        user.setId(accountModel.getUserId());
        user.setSalt(salt);
        user.setPassword(newEncryptPassword);
        userService.updateUser(user);
    }

    /**
     * 绑定手机，邮箱
     * @param type  1-手机，2-邮箱
     * @param receiver
     */
    @RequestMapping(value = "/bind", method = RequestMethod.PUT)
    @ApiOperation(value = "绑定手机、邮箱")
    @ActionLog(actionName = "绑定手机、邮箱")
    public void bind(@RequestParam(value = "type") Integer type,
                     @RequestParam(value = "receiver") String receiver,
                     @RequestParam(value = "code") String code){
        Assert.isTrue(type.equals(FIND_TYPE_EMAIL) || type.equals(FIND_TYPE_PHONE), "找回方式仅支持：1-邮箱找回，2-手机找回");
        validationCodeService.validateCode(receiver, code);
        UserModel userModel;
        User user = new User();
        if(type.equals(FIND_TYPE_EMAIL)){
            org.springframework.util.Assert.isTrue(EmailValidator.getInstance().isValid(receiver), "请输入正确格式的邮箱");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.isNull(userModel, "该邮箱已被绑定");
            user.setEmail(receiver);
        }else if(type.equals(FIND_TYPE_PHONE)){
            org.springframework.util.Assert.isTrue(PhoneValidator.getInstance().isValid(receiver), "请输入正确格式的手机号码");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.isNull(userModel, "该手机号码已被绑定");
            user.setPhoneNumber(receiver);
        }
        user.setId(getCurrentUserId());
        userService.updateUser(user);
        validationCodeService.cleanCode(receiver);
    }

    /**
     * 更换头像
     * @param file
     */
    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    @ApiOperation(value = "更换头像")
    @ActionLog(actionName = "更换头像")
    public void updateAvatar(@RequestPart(value = "file") MultipartFile file) {
        if(file.isEmpty()){
            return;
        }
        String userId = getCurrentUserId();
        userService.updateAvatar(userId, file);
    }



}
