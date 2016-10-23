package com.xmomen.module.notification.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "xmo_notification_text")
public class NotificationText extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 提交时间
     */
    private Date createTime;

    /**
     * 标题
     */
    private String titel;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 内容
     */
    private String body;

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

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if(createTime == null){
              removeValidField("createTime");
              return;
        }
        addValidField("createTime");
    }

    @Column(name = "TITEL")
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
        if(titel == null){
              removeValidField("titel");
              return;
        }
        addValidField("titel");
    }

    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if(type == null){
              removeValidField("type");
              return;
        }
        addValidField("type");
    }

    @Column(name = "BODY")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
        if(body == null){
              removeValidField("body");
              return;
        }
        addValidField("body");
    }
}