package com.xmomen.module.attachment.model;

import lombok.Data;
import com.xmomen.framework.model.BaseQuery;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
public @Data class AttachmentQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String attachmentKey;
    private String id;
    private String[] ids;
    private String[] excludeIds;

}
