package com.xmomen.module.core.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.shiro.realm.UserRealm;
import com.xmomen.module.user.entity.User;
import com.xmomen.module.user.entity.UserExample;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Created by tanxinzheng on 16/9/2.
 */
@Service
public class AccountService {

    @Autowired
    MybatisDao mybatisDao;

    public AccountModel getAccountByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        userExample.or().andEmailEqualTo(username);
        userExample.or().andPhoneNumberEqualTo(username);
        User user = mybatisDao.selectOneByExample(userExample);
        if(user != null){
            AccountModel accountModel = new AccountModel();
            accountModel.setLocked(user.getIsLock());
            accountModel.setUsername(user.getUsername());
            accountModel.setEmail(user.getEmail());
            accountModel.setNickname(user.getNickname());
            accountModel.setPhoneNumber(user.getPhoneNumber());
            return accountModel;
        }
        return null;
    }

    public User getUserByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        userExample.or().andEmailEqualTo(username);
        userExample.or().andPhoneNumberEqualTo(username);
        User user = mybatisDao.selectOneByExample(userExample);
        if(user != null){
            return user;
        }
        return null;
    }
}
