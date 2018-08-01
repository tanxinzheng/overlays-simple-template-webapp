package com.xmomen.module.core.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by tanxinzheng on 18/5/15.
 */
@Data
public class UploadModel {

    private String uploader;
    private String fileType;
    private long fileSize;
    private String fileKey;
    private String filename;
    private Date uploadDate;
}
