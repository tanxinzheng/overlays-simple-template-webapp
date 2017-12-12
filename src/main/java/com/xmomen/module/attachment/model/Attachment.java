package com.xmomen.module.attachment.model;

import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreter;
import lombok.Data;
import com.xmomen.framework.model.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
public @Data class Attachment extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 附件所属组 */
    private String attachmentGroup;
    /** 附件KEY */
    @DictionaryInterpreter(index = DictionaryIndex.ATTACHMENT_KEY, fieldName = "attachmentUrl")
    private String attachmentKey;
    /** 附件大小 */
    private Long attachmentSize;
    /** 附件URL */
    private String attachmentPath;
    /** 附件后缀 */
    private String attachmentSuffix;
    /** 原名称 */
    private String originName;
    /** 上传时间 */
    private Date uploadTime;
    /** 上传人ID */
    @DictionaryInterpreter(fieldName = "uploadUserName", index = DictionaryIndex.USER_ID)
    private String uploadUserId;
    /** 关联ID */
    private String relationId;
    /** 是否私有 */
    private Boolean isPrivate;


}
