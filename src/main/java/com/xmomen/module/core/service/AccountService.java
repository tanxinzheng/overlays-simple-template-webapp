package com.xmomen.module.core.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.user.entity.User;
import com.xmomen.module.user.entity.UserExample;
import com.xmomen.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            accountModel.setLocked(user.getLocked() == 1 ? true : false);
            accountModel.setUsername(user.getUsername());
            accountModel.setSalt(user.getSalt());
            return accountModel;
        }
        return null;
    }
}
