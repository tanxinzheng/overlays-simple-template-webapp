package com.xmomen.framework.fss;

import com.xmomen.framework.fss.model.FileStorageInfo;
import com.xmomen.framework.fss.model.FileStorageResult;

import java.io.IOException;
import java.io.InputStream;


public interface FileStoreService {

    /**
     * 是否存在文件
     * @param filePathAndName
     * @return
     */
    public boolean existFile(String filePathAndName);

    /**
     * 根据路径获取文件流
     * @return
     * @throws IOException
     */
    public FileStorageResult getFile(String filePathAndName);

    /**
     * 保存文件
     * @param fileStorageInfo
     * @return
     * @throws IOException
     */
    public FileStorageResult newFile(FileStorageInfo fileStorageInfo);

    /**
     * 删除文件
     * @param filePathAndName
     */
    public boolean deleteFile(String filePathAndName);

}
