package com.xmomen.module.security;

import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserGroupService;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

/**
 * Created by tanxinzheng on 17/8/18.
 */
//@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userService.getOneUserModelByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            Set<String> roles = accountService.findRoles(user.getId());
            return JwtUserFactory.create(user, roles);
        }
    }
}
