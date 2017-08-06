package com.xmomen.module.attachment.model;

import com.xmomen.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.attachment.model.Attachment;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.String;
import java.lang.Integer;
import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
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
    private String attachmentKey;
    /** 附件大小 */
    @Excel(name = "附件大小")
    @NotNull(message = "附件大小为必填项")
    @Range(max = 999999999, min = -999999999, message = "附件大小数值范围[999999999,-999999999]")
    private Integer attachmentSize;
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
    private String uploadUserId;
    /** 关联ID */
    @Excel(name = "关联ID")
    @Length(max = 32, message = "关联ID字符长度限制[0,32]")
    private String relationId;
    /** 是否私有 */
    @Excel(name = "是否私有")
    @NotNull(message = "是否私有为必填项")
    @Range(max = 999999999, min = -999999999, message = "是否私有数值范围[999999999,-999999999]")
    private Integer isPrivate;

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
