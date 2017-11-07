package com.xmomen.framework.fss.adapters;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.xmomen.framework.fss.FileStoreService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
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
@Service
public class FileStoreServiceByOss implements FileStoreService, InitializingBean {

    public Logger log = LoggerFactory.getLogger(FileStoreServiceByOss.class);

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
        try {
            log.debug("accessKeyId initializing.........");
//            createOSSClientConnection();
        } catch (OSSException ex) {
            log.error("OSSClient initial failed - " + ex.getMessage(), ex);
        }
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
        if(client == null){
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
        }
    }

    public OSSClient getClient(){
        return client;
    }

    @Override
    public boolean existFile(String filePath) {
        if(StringUtils.isEmpty(filePath)){
            return false;
        }
        reconnectionOSSClient();
        try{
            OSSObject ossObject = client.getObject(bucketName, filePath);
            if(ossObject != null){
                return true;
            }
        }catch (OSSException e){
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public InputStream getFile(String filePath) throws IOException {
        try {
            createOSSClientConnection();
            OSSObject ossObject = client.getObject(bucketName, filePath);
            return ossObject.getObjectContent();
        } catch (OSSException oe) {
            log.info("OSSClient getFile failed - OSSException: "+oe.getMessage(), oe);
            throw new IOException(oe.getMessage());
        } catch (ClientException ce) {
            log.info("OSSClient getFile failed - ClientException: "+ce.getMessage(), ce);
            throw new IOException(ce.getMessage());
        } finally {
            reconnectionOSSClient();
        }
    }

    @Override
    public void newFile(String filePathAndName, InputStream inputStream) throws IOException {
        try {
            reconnectionOSSClient();
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(inputStream.available());
            client.putObject(bucketName, filePathAndName, inputStream, meta);
        } catch (OSSException oe) {
            log.info("newFile failed - OSSException: "+oe.getMessage(), oe);
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(inputStream.available());
            client.putObject(bucketName, filePathAndName, inputStream, meta);
        } catch (ClientException ce) {
            log.info("newFile failed - ClientException: "+ce.getMessage(), ce);
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(inputStream.available());
            client.putObject(bucketName, filePathAndName, inputStream, meta);
        } finally {
            reconnectionOSSClient();
        }
    }

    @Override
    public void deleteFile(String filePathAndName) {
        client.deleteObject(bucketName, filePathAndName);
    }

    @Override
    public boolean moveFile(String srcFileName, String destDirName) {
        try {
            Pattern pattern = Pattern.compile("[^/]+/");
            Matcher matcher = pattern.matcher(srcFileName);
            String destFileName = matcher.replaceFirst("")+destDirName;
            copyFile(srcFileName, destFileName, true);
            deleteFile(srcFileName);
        } catch (OSSException ex) {
            log.info("moveFile failed - OSSException: "+ex.getMessage());
            reconnectionOSSClient();
            return false;
        } catch (ClientException ce) {
            log.info("moveFile failed - ClientException: "+ce.getMessage());
            reconnectionOSSClient();
            return false;
        }
        return true;
    }

    @Override
    public boolean copyFile(String srcFileName, String destFileName, boolean overlay) {
        try {
            try {
                OSSObject object = client.getObject(bucketName, srcFileName);
                object.getObjectContent().close();
            } catch (OSSException ex) {
                log.info("key srcFileName does not exist - "+ex.getMessage(), ex);
                return false;
            } catch (IOException e) {
                log.info(e.getMessage(), e);
            }
            CopyObjectResult result = client.copyObject(bucketName, srcFileName, bucketName, destFileName);
            log.debug("ETag: " + result.getETag() + " LastModified: " + result.getLastModified());
            deleteFile(srcFileName);
        } catch (OSSException oe) {
            log.info("copyFile failed - OSSException: "+oe.getMessage(), oe);
            reconnectionOSSClient();
            return false;
        } catch (ClientException ce) {
            log.info("copyFile failed - ClientException: "+ce.getMessage());
            reconnectionOSSClient();
            CopyObjectResult result = client.copyObject(bucketName, srcFileName, bucketName, destFileName);
            log.debug("ETag: " + result.getETag() + " LastModified: " + result.getLastModified());
            deleteFile(srcFileName);
        }
        return true;
    }


}
