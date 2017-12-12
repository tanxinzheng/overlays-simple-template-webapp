package com.xmomen.module.core.model;

import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tanxinzheng on 16/9/2.
 */
public @Data class AccountModel implements Serializable {

    private String userId;
    private String username;
    private String nickname;
    private String email;
    private String phoneNumber;
    private Date createdTime;
    private Date lastLoginTime;
    private Boolean locked;
    @DictionaryInterpreter(index = DictionaryIndex.ATTACHMENT_KEY, fieldName = "avatarUrl")
    private String avatar;

    private List<NavItem> navItems;
    private List<Role> roles;

    @Data
    public static class NavItem implements Serializable {
        private String url;
        private String name;
        private String title;
    }

    @Data
    public static class Role implements Serializable {
        private String roleName;
        private String description;
        private Boolean available;
    }
}
