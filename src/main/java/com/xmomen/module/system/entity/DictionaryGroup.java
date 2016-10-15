package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sys_dictionary_group")
public class DictionaryGroup extends BaseMybatisModel {
    /**
     * 
     */
    private String id;

    /**
     * 字典编号
     */
    private String dictionaryType;

    /**
     * 字典描述
     */
    private String dictionaryDesc;

    /**
     * 激活
     */
    private Boolean active;

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

    @Column(name = "DICTIONARY_TYPE")
    public String getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(String dictionaryType) {
        this.dictionaryType = dictionaryType;
        if(dictionaryType == null){
              removeValidField("dictionaryType");
              return;
        }
        addValidField("dictionaryType");
    }

    @Column(name = "DICTIONARY_DESC")
    public String getDictionaryDesc() {
        return dictionaryDesc;
    }

    public void setDictionaryDesc(String dictionaryDesc) {
        this.dictionaryDesc = dictionaryDesc;
        if(dictionaryDesc == null){
              removeValidField("dictionaryDesc");
              return;
        }
        addValidField("dictionaryDesc");
    }

    @Column(name = "ACTIVE")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
        if(active == null){
              removeValidField("active");
              return;
        }
        addValidField("active");
    }
}