package com.xmomen.module.notification.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "xmo_system_notification")
public class SystemNotification extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 发送者主键
     */
    private String senderId;

    /**
     * 接收者主键
     */
    private String receiverId;

    /**
     * 内容主键
     */
    private String textId;

    /**
     * 已读
     */
    private Boolean readed;

    /**
     * 状态
     */
    private String status;

    /**
     * 过期时间
     */
    private Date expireTime;

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

    @Column(name = "SENDER_ID")
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
        if(senderId == null){
              removeValidField("senderId");
              return;
        }
        addValidField("senderId");
    }

    @Column(name = "RECEIVER_ID")
    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
        if(receiverId == null){
              removeValidField("receiverId");
              return;
        }
        addValidField("receiverId");
    }

    @Column(name = "TEXT_ID")
    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
        if(textId == null){
              removeValidField("textId");
              return;
        }
        addValidField("textId");
    }

    @Column(name = "READED")
    public Boolean getReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
        if(readed == null){
              removeValidField("readed");
              return;
        }
        addValidField("readed");
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if(status == null){
              removeValidField("status");
              return;
        }
        addValidField("status");
    }

    @Column(name = "EXPIRE_TIME")
    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
        if(expireTime == null){
              removeValidField("expireTime");
              return;
        }
        addValidField("expireTime");
    }
}