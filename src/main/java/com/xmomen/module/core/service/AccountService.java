package com.xmomen.module.core.service;

import com.xmomen.commons.StringUtilsExt;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.model.GroupQuery;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.PermissionQuery;
import com.xmomen.module.authorization.service.GroupService;
import com.xmomen.module.authorization.service.PermissionService;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.model.Register;
import com.xmomen.module.shiro.PasswordHelper;
import com.xmomen.module.shiro.realm.UserRealm;
import com.xmomen.module.user.model.UserModel;
import com.xmomen.module.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tanxinzheng on 16/9/2.
 */
@Service
public class AccountService {

    private static final String defaultSessionModelKey = "account_model_session";

    private String sessionModelKey = defaultSessionModelKey;

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    PermissionService permissionService;

    /**
     * 查询帐号
     * @param username
     * @return
     */
    public AccountModel getAccountModelByUsername(String username) {
        UserModel user = userService.getOneUserModelByUsername(username);
        if(user != null){
            AccountModel accountModel = new AccountModel();
            accountModel.setLocked(user.getLocked());
            accountModel.setUsername(user.getUsername());
            accountModel.setEmail(user.getEmail());
            accountModel.setNickname(user.getNickname());
            accountModel.setPhoneNumber(user.getPhoneNumber());
            accountModel.setUserId(user.getId());
            return accountModel;
        }
        return null;
    }

    /**
     * 用户注册
     * @param register
     * @return
     */
    public AccountModel register(Register register) throws AccountException {
        SimpleAccount simpleAccount = getAccountByUsername(register.getUsername());
        if(simpleAccount != null){
            throw new AccountException("此用户名已被注册");
        }
        SimpleAccount emailAccount = getAccountByUsername(register.getEmail());
        if(emailAccount != null){
            throw new AccountException("此邮箱已被注册");
        }
        String salt = StringUtilsExt.getUUID(32);
        String encryptPassword = PasswordHelper.encryptPassword(register.getPassword(), salt);
        UserModel userCreate = new UserModel();
        userCreate.setEmail(register.getEmail());
        userCreate.setPhoneNumber(register.getPhoneNumber());
        userCreate.setCreateDate(new Date());
        if(StringUtils.trimToNull(register.getNickname()) == null){
            userCreate.setNickname(register.getUsername());
        }else{
            userCreate.setNickname(register.getNickname());
        }
        userCreate.setUsername(register.getUsername());
        userCreate.setSalt(salt);
        userCreate.setPassword(encryptPassword);
        UserModel user = userService.createUser(userCreate);
        AccountModel accountModel = new AccountModel();
        BeanUtils.copyProperties(user, accountModel);
        accountModel.setUserId(user.getId());
        return accountModel;
    }

    /**
     * 获取SessionModel
     * @return
     */
    public AccountModel getSessionModel(){
        AccountModel accountModel = (AccountModel) SecurityUtils.getSubject().getSession().getAttribute(sessionModelKey);
        if(accountModel == null){
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            accountModel = getAccountModelByUsername(username);
            SecurityUtils.getSubject().getSession().setAttribute(sessionModelKey, accountModel);
        }
        return accountModel;
    }

    /**
     * 查询帐号
     * @param username
     * @return
     */
    public SimpleAccount getAccountByUsername(String username) {
        UserModel user = userService.getOneUserModelByUsername(username);
        if(user != null){
            SimpleAccount account = new SimpleAccount(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), UserRealm.class.getName());
            if(user.getLocked() != null){
                account.setLocked(user.getLocked());
            }
            return account;
        }
        return null;
    }

    public void resetPassword(String email){

    }

    public void validResetPassword(String email, String token, String mail){

    }

    /**
     * 查询角色
     * @return
     */
    public Set<String> findRoles(){
        AccountModel accountModel = getSessionModel();
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setUserId(accountModel.getUserId());
        groupQuery.setHasBindGroup(true);
        List<GroupModel> groupList = groupService.getGroupModelList(groupQuery);
        Set<String> roles = new HashSet<>();
        for (GroupModel groupModel : groupList) {
            roles.add(groupModel.getGroupCode());
        }
        return roles;
    }

    /**
     * 查询权限
     * @return
     */
    public Set<String> findPermissions(){
        AccountModel accountModel = getSessionModel();
        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.setUserId(accountModel.getUserId());
//        permissionQuery.setHasBindPermission(true);
        List<PermissionModel> permissionModelList = permissionService.getPermissionModelList(permissionQuery);
        Set<String> permissions = new HashSet<>();
        for (PermissionModel permissionModel : permissionModelList) {
            permissions.add(permissionModel.getPermissionCode());
        }
        return permissions;
    }

    public String getSessionModelKey() {
        return sessionModelKey;
    }

    public void setSessionModelKey(String sessionModelKey) {
        this.sessionModelKey = sessionModelKey;
    }
}
