package com.xmomen.framework.fss.adapters;

import com.xmomen.framework.fss.model.FileStorageInfo;
import com.xmomen.framework.fss.model.FileStorageResult;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by tanxinzheng on 2018/7/23.
 */
public class FileStoreServiceByOssTest {

    private FileStoreServiceByOss fileOperation;

    private InputStream inputStream;
    private FileStorageResult result;
    @Before
    public void setUp() throws Exception {
        if(fileOperation == null){
            fileOperation = new FileStoreServiceByOss();
            fileOperation.setAccessKeyId("LTAIQ9tAnQKgixOa");
            fileOperation.setAccessKeySecret("SvUzBFGETJ3k9DUY0krXEKLYEpOsFF");
            fileOperation.setBucketName("xmomen-test");
            fileOperation.setEndpoint("oss-cn-hangzhou.aliyuncs.com");
        }
        String demoFile = "/Users/jeng/xmomen-repo/webapp/overlays-simple-template-webapp/src/test/resources/demo/logo.png";
        inputStream = new FileInputStream(new File(demoFile));
    }

    @After
    public void tearDown() throws Exception {
        if(result != null && result.getStoragePath() != null){
            fileOperation.deleteFile(result.getStoragePath());
        }
    }

    @Test
    public void existFile() throws Exception {
        FileStorageInfo fileStorageInfo = new FileStorageInfo("png", inputStream);
        FileStorageResult result = fileOperation.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
        Boolean isSuccess = fileOperation.existFile(result.getStoragePath());
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void getFile() throws Exception {
        FileStorageInfo fileStorageInfo = new FileStorageInfo("png", inputStream);
        FileStorageResult result = fileOperation.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
        result = fileOperation.getFile(result.getStoragePath());
        Assert.assertTrue(result.getFileSize() > 0);
    }

    @Test
    public void newFile() throws Exception {
        FileStorageInfo fileStorageInfo = new FileStorageInfo("png", inputStream);
        FileStorageResult result = fileOperation.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void deleteFile() throws Exception {
    }

}