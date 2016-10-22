package com.xmomen.module.user.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "xmo_user")
public class User extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String nickname;

    /**
     * 密码盐值
     */
    private String salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 锁定
     */
    private Boolean locked;

    /**
     * 注册时间
     */
    private Date createDate;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        if(username == null){
              removeValidField("username");
              return;
        }
        addValidField("username");
    }

    @Column(name = "NICKNAME")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        if(nickname == null){
              removeValidField("nickname");
              return;
        }
        addValidField("nickname");
    }

    @Column(name = "SALT")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
        if(salt == null){
              removeValidField("salt");
              return;
        }
        addValidField("salt");
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        if(password == null){
              removeValidField("password");
              return;
        }
        addValidField("password");
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if(email == null){
              removeValidField("email");
              return;
        }
        addValidField("email");
    }

    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        if(phoneNumber == null){
              removeValidField("phoneNumber");
              return;
        }
        addValidField("phoneNumber");
    }

    @Column(name = "LOCKED")
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
        if(locked == null){
              removeValidField("locked");
              return;
        }
        addValidField("locked");
    }

    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
        if(createDate == null){
              removeValidField("createDate");
              return;
        }
        addValidField("createDate");
    }

    @Column(name = "LAST_LOGIN_TIME")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        if(lastLoginTime == null){
              removeValidField("lastLoginTime");
              return;
        }
        addValidField("lastLoginTime");
    }
}