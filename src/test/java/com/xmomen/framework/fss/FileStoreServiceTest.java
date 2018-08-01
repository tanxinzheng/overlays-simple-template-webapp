package com.xmomen.framework.fss;

import com.xmomen.framework.fss.adapters.FileStoreServiceByOss;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by tanxinzheng on 17/8/6.
 */
@Ignore
public class FileStoreServiceTest {

    private FileStoreServiceByOss fileOperation;

    @Before
    public void setUp() throws Exception {
        if(fileOperation == null){
            fileOperation = new FileStoreServiceByOss();
            fileOperation.setAccessKeyId("LTAIQ9tAnQKgixOa");
            fileOperation.setAccessKeySecret("SvUzBFGETJ3k9DUY0krXEKLYEpOsFF");
            fileOperation.setBucketName("xmomen-test");
            fileOperation.setEndpoint("oss-cn-hangzhou.aliyuncs.com");
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void existFile() throws Exception {
    }

    @Test
    public void newFile() throws Exception {

    }

    @Test
    public void getFile() throws Exception {
//        InputStream inputStream = fileOperation.getFile("TEST");
//        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/jeng/xmomen-repo/webapp/overlays-simple-template-webapp/src/test/resources/demo.png"));
    }


    @Test
    public void delFile() throws Exception {
    }

    @Test
    public void moveFile() throws Exception {
    }

    @Test
    public void copyFile() throws Exception {
    }

}