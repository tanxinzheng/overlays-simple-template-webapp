package com.xmomen.framework.fss.adapters;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.sun.org.apache.regexp.internal.RE;
import com.xmomen.framework.fss.FileStoreService;
import com.xmomen.framework.fss.model.FileStorageInfo;
import com.xmomen.framework.fss.model.FileStorageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注：图片等静态资源存储在公共读类的bucket，而私密性文件应存储在私有类的bucket
 * Created by Jeng on 15/6/24.
 */
@Slf4j
@Component
public class FileStoreServiceByOss implements FileStoreService, InitializingBean {

    @Value(value = "${oss.accessKeyId}")
    private String accessKeyId;
    @Value(value = "${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value(value = "${oss.endpoint}")
    private String endpoint;
    @Value(value = "${oss.bucketName}")
    private String bucketName;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    private static OSSClient client;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(accessKeyId, "accessKeyId must be not null or empty");
        Assert.hasText(accessKeySecret, "accessKeyId must be not null or empty");
        Assert.hasText(endpoint, "accessKeyId must be not null or empty");
        Assert.hasText(bucketName, "accessKeyId must be not null or empty");
    }

    private void reconnectionOSSClient() {
        createOSSClientConnection();
    }


    /**
     * 创建ossClient连接
     */
    private void createOSSClientConnection() {
        Assert.hasText(accessKeyId, "accessKeyId must be not null or empty");
        Assert.hasText(accessKeySecret, "accessKeyId must be not null or empty");
        Assert.hasText(endpoint, "accessKeyId must be not null or empty");
        Assert.hasText(bucketName, "accessKeyId must be not null or empty");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setMaxConnections(3500);
        conf.setConnectionTimeout(3500);
        conf.setMaxErrorRetry(6);
s..        if(client == null){
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
        }
    }

    public OSSClient getClient(){
        if(client == null) {
            createOSSClientConnection();
        }
        return client;
    }

    @Override
    public boolean existFile(String filePath) {
        if(StringUtils.isEmpty(filePath)){
            return false;
        }
        reconnectionOSSClient();
        try{
            OSSObject ossObject = getClient().getObject(bucketName, filePath);
            if(ossObject != null){
                return true;
            }
        }catch (OSSException e){
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public FileStorageResult getFile(String filePath) {
        try {
            createOSSClientConnection();
            OSSObject ossObject = getClient().getObject(bucketName, filePath);
            return FileStorageResult.SUCCESS(filePath, ossObject.getObjectContent());
        } catch (Exception e) {
            log.error("OSSClient getObject fail, filePath: {}, error message: {}", filePath, e.getMessage(), e);
        }
        return FileStorageResult.FAIL();
    }

    @Override
    public FileStorageResult newFile(FileStorageInfo fileStorageInfo) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(fileStorageInfo.getContent().length);
            PutObjectResult result = getClient().putObject(bucketName,
                    fileStorageInfo.getFullPath(),
                    fileStorageInfo.getInputStream(), meta);
            return FileStorageResult.SUCCESS(fileStorageInfo.getFullPath());
        } catch (Exception e) {
            log.error("OSSClient delete file fail, file path: {}, message: {}", fileStorageInfo.getFullPath(), e.getMessage(), e);
        }
        return FileStorageResult.FAIL();
    }

    /**
     * 删除文件
     * @param filePathAndName
     * @return
     */
    @Override
    public boolean deleteFile(String filePathAndName) {
        log.debug("OSSClient delete file, file path: {}", filePathAndName);
        try {
            client.deleteObject(bucketName, filePathAndName);
            return Boolean.TRUE;
        } catch (Exception e){
            log.error("OSSClient delete file fail, file path: {}, message: {}", filePathAndName, e.getMessage(), e);
        }
        return Boolean.FALSE;
    }


}
