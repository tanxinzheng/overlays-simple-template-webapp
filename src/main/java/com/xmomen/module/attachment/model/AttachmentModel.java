package com.xmomen.module.attachment.model;

import com.xmomen.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import com.xmomen.framework.web.json.TransferFormatType;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 23:28:37
 * @version 1.0.0
 */
@ExcelTarget(value = "AttachmentModel")
public @Data class AttachmentModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 附件所属组 */
    @Excel(name = "附件所属组")
    @NotBlank(message = "附件所属组为必填项")
    @Length(max = 20, message = "附件所属组字符长度限制[0,20]")
    private String attachmentGroup;
    /** 附件KEY */
    @Excel(name = "附件KEY")
    @NotBlank(message = "附件KEY为必填项")
    @Length(max = 50, message = "附件KEY字符长度限制[0,50]")
    @DictionaryInterpreter(index = DictionaryIndex.ATTACHMENT_KEY, fieldName = "attachmentUrl")
    private String attachmentKey;
    /** 附件大小 */
    @Excel(name = "附件大小")
    @NotNull(message = "附件大小为必填项")
    private Long attachmentSize;
    /** 附件URL */
    @Excel(name = "附件URL")
    @NotBlank(message = "附件URL为必填项")
    @Length(max = 200, message = "附件URL字符长度限制[0,200]")
    private String attachmentPath;
    /** 附件后缀 */
    @Excel(name = "附件后缀")
    @NotBlank(message = "附件后缀为必填项")
    @Length(max = 10, message = "附件后缀字符长度限制[0,10]")
    private String attachmentSuffix;
    /** 原名称 */
    @Excel(name = "原名称")
    @NotBlank(message = "原名称为必填项")
    @Length(max = 100, message = "原名称字符长度限制[0,100]")
    private String originName;
    /** 上传时间 */
    @Excel(name = "上传时间")
    @NotNull(message = "上传时间为必填项")
    private Date uploadTime;
    /** 上传人ID */
    @Excel(name = "上传人ID")
    @NotBlank(message = "上传人ID为必填项")
    @Length(max = 32, message = "上传人ID字符长度限制[0,32]")
    @DictionaryInterpreter(fieldName = "uploadUserName", index = DictionaryIndex.USER_ID, outFormat = TransferFormatType.Object)
    private String uploadUserId;
    /** 关联ID */
    @Excel(name = "关联ID")
    @Length(max = 32, message = "关联ID字符长度限制[0,32]")
    private String relationId;
    /** 是否私有 */
    @Excel(name = "是否私有")
    @NotNull(message = "是否私有为必填项")
    private Boolean isPrivate;

    /**
     * 全路径key
     */
    private String fullKey;

    public String getFullKey() {
        return this.attachmentPath + File.separator + this.attachmentKey;
    }

    /**
    * Get Attachment Entity Object
    * @return
    */
    @JsonIgnore
    public Attachment getEntity(){
        Attachment attachment = new Attachment();
        BeanUtils.copyProperties(this, attachment);
        return attachment;
    }


}
