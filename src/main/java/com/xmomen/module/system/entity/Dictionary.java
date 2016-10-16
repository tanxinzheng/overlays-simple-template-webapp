package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "xmo_dictionary")
public class Dictionary extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 字典类型
     */
    private String dictionaryType;

    /**
     * 显示值
     */
    private String showValue;

    /**
     * 实际值
     */
    private String codeValue;

    /**
     * 排序
     */
    private Integer sortValue;

    /**
     * 激活
     */
    private Boolean active;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 显示
     */
    private Boolean show;

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

    @Column(name = "SHOW_VALUE")
    public String getShowValue() {
        return showValue;
    }

    public void setShowValue(String showValue) {
        this.showValue = showValue;
        if(showValue == null){
              removeValidField("showValue");
              return;
        }
        addValidField("showValue");
    }

    @Column(name = "CODE_VALUE")
    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
        if(codeValue == null){
              removeValidField("codeValue");
              return;
        }
        addValidField("codeValue");
    }

    @Column(name = "SORT_VALUE")
    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
        if(sortValue == null){
              removeValidField("sortValue");
              return;
        }
        addValidField("sortValue");
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

    @Column(name = "PARENT_ID")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
        if(parentId == null){
              removeValidField("parentId");
              return;
        }
        addValidField("parentId");
    }

    @Column(name = "SHOW")
    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
        if(show == null){
              removeValidField("show");
              return;
        }
        addValidField("show");
    }
}