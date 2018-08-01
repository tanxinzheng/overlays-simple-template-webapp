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


/**
 * Created by tanxinzheng on 2018/7/20.
 */
public class FileStoreServiceByFastDFSTest {

    private FileStoreServiceByFastDFS fileStoreService;

    private InputStream inputStream;

    private String targetPath = "/Users/jeng/xmomen-repo/webapp/overlays-simple-template-webapp/src/test/resources/demo/target";
    private String demoFile = "/Users/jeng/xmomen-repo/webapp/overlays-simple-template-webapp/src/test/resources/demo/logo.png";

    private FileStorageInfo fileStorageInfo;
    private FileStorageResult fileStorageResult;

    @Before
    public void setUp() throws Exception {
        fileStoreService = new FileStoreServiceByFastDFS();
        inputStream = new FileInputStream(new File(demoFile));
        fileStorageInfo = new FileStorageInfo("png", inputStream);
    }

    @After
    public void tearDown() throws Exception {
        if(fileStorageResult != null && fileStorageResult.getStoragePath() != null){
            fileStoreService.deleteFile(fileStorageResult.getStoragePath());
        }
    }

    @Test
    public void newFile() throws Exception {
        InputStream inputStream = new FileInputStream(new File(demoFile));
        FileStorageInfo fileStorageInfo = new FileStorageInfo("png", inputStream);
        FileStorageResult result = fileStoreService.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void existFile() throws Exception {
        FileStorageResult putResult = fileStoreService.newFile(fileStorageInfo);
        Boolean isSuccess = fileStoreService.existFile(putResult.getStoragePath());
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void getFile() throws Exception {
        FileStorageResult putResult = fileStoreService.newFile(fileStorageInfo);
        Assert.assertTrue("上传文件失败", putResult.isSuccess());
        FileStorageResult getResult = fileStoreService.getFile(putResult.getStoragePath());
        Assert.assertTrue("获取文件失败", getResult.isSuccess());
        Assert.assertNotNull(getResult.getInputStream());
        FileUtils.copyInputStreamToFile(getResult.getInputStream(), new File(
                targetPath + File.separator + "demo2." + getResult.getFileExt()));
    }

    @Test
    public void deleteFile() throws Exception {
        FileStorageResult result = fileStoreService.newFile(fileStorageInfo);
        Boolean isSuccess = fileStoreService.deleteFile(result.getStoragePath());
        Assert.assertTrue(isSuccess);
    }
}