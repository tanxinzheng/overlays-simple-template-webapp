package com.xmomen.module.core.model;

import lombok.Data;

import java.io.Serializable;
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
    private Boolean locked;

    private List<NavItem> navItems;
    private List<Role> roles;

    public static class NavItem implements Serializable {
        private String url;
        private String name;
        private String title;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class Role implements Serializable {
        private String role;
        private String description;
        private Boolean available;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean isAvailable() {
            return available;
        }

        public void setAvailable(Boolean available) {
            this.available = available;
        }
    }
}
