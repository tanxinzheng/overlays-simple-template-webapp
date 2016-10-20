package com.xmomen.module.core.service;

import com.xmomen.commons.StringUtilsExt;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.model.Register;
import com.xmomen.module.shiro.PasswordHelper;
import com.xmomen.module.shiro.realm.UserRealm;
import com.xmomen.module.user.entity.User;
import com.xmomen.module.user.entity.UserExample;
import com.xmomen.module.user.model.UserCreate;
import com.xmomen.module.user.model.UserModel;
import com.xmomen.module.user.service.UserService;
import org.apache.shiro.authc.Account;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;

/**
 * Created by tanxinzheng on 16/9/2.
 */
@Service
public class AccountService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    UserService userService;

    /**
     * 查询帐号
     * @param username
     * @return
     */
    public AccountModel getAccountByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        userExample.or().andEmailEqualTo(username);
        userExample.or().andPhoneNumberEqualTo(username);
        User user = mybatisDao.selectOneByExample(userExample);
        if(user != null){
            AccountModel accountModel = new AccountModel();
            accountModel.setLocked(user.getLocked());
            accountModel.setUsername(user.getUsername());
            accountModel.setEmail(user.getEmail());
            accountModel.setNickname(user.getNickname());
            accountModel.setPhoneNumber(user.getPhoneNumber());
            return accountModel;
        }
        return null;
    }

    /**
     * 用户注册
     * @param register
     * @return
     */
    public AccountModel register(Register register){
        String salt = StringUtilsExt.getUUID(12);
        String encryptPassword = PasswordHelper.encryptPassword(register.getPassword(), salt);
        UserCreate userCreate = new UserCreate();
        userCreate.setEmail(register.getEmail());
        userCreate.setPhoneNumber(register.getPhoneNumber());
        userCreate.setNickname(register.getNickname());
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
     * 查询帐号
     * @param username
     * @return
     */
    public SimpleAccount getUserByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        userExample.or().andEmailEqualTo(username);
        userExample.or().andPhoneNumberEqualTo(username);
        User user = mybatisDao.selectOneByExample(userExample);
        if(user != null){
            SimpleAccount account = new SimpleAccount(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), UserRealm.class.getName());
            account.setLocked(user.getLocked());
            return account;
        }
        return null;
    }

    /**
     * 查询角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username){
        return null;
    }

    /**
     * 查询权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username){
        return null;
    }

}
