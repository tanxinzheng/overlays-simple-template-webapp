package com.xmomen.framework.fss.adapters;

import com.xmomen.framework.fss.FileStoreService;
import com.xmomen.framework.fss.model.FileStorageInfo;
import com.xmomen.framework.fss.model.FileStorageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by tanxinzheng on 2018/7/20.
 */
@Slf4j
@Component
public class FileStoreServiceByFastDFS implements FileStoreService {

    private static TrackerServer trackerServer;
    private static TrackerClient trackerClient;

    private static StorageServer storageServer;
    private static StorageClient storageClient;

    static {
        try {
            String filePath = new ClassPathResource("fastdfs_client.conf").getFile().getAbsolutePath();;
            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (Exception e) {
            log.error("FastDFS Client Init Fail!", e);
        }
    }

    private String[] parserFullPath(String filePath){
        if(StringUtils.isBlank(filePath)){
            return null;
        }
        return filePath.split(File.separator);
    }

    private String getGroupName(String filePath){
        return filePath.split(File.separator)[0];
    }

    private String getFileName(String filePath){
        return filePath.split(File.separator)[1];
    }

    private String getFileExt(String filePath){
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

    /**
     * 是否存在文件
     *
     * @param filePath
     * @return
     */
    @Override
    public boolean existFile(String filePath) {
        String[] parserFullPath = parserFullPath(filePath);
        if(ArrayUtils.isEmpty(parserFullPath)){
            return Boolean.FALSE;
        }
        try {
            storageClient.get_file_info(parserFullPath[0], parserFullPath[1]);
            return Boolean.TRUE;
        } catch (IOException e) {
            log.error("FastDFSClient storageClient.get_file_info fail, filePath: {}, error message: {}", filePath, e.getMessage(), e);
        } catch (MyException e) {
            log.error("FastDFSClient storageClient.get_file_info fail, filePath: {}, error message: {}", filePath, e.getMessage(), e);
        }
        return Boolean.FALSE;
    }

    /**
     * 根据路径获取文件流
     *
     * @param filePath
     * @return
     */
    @Override
    public FileStorageResult getFile(String filePath) {
        try {
            String[] full = filePath.split(File.separator, 2);
            byte[] b = storageClient.download_file(full[0], full[1]);
            FileStorageResult fileStorageResult = FileStorageResult.SUCCESS(filePath);
            fileStorageResult.setContent(b);
            return fileStorageResult;
        } catch (Exception e) {
            log.error("FastDFSClient storageClient.download_file fail, filePath: {}, error message: {}", filePath, e.getMessage(), e);
        }
        return FileStorageResult.FAIL();
    }

    /**
     * 保存文件
     *
     * @return
     * @throws IOException
     */
    @Override
    public FileStorageResult newFile(FileStorageInfo fileStorageInfo) {
        try {
            String[] uploadResults = storageClient.upload_file(fileStorageInfo.getContent(),
                    fileStorageInfo.getFileExt(),
                    null);
            if(ArrayUtils.isEmpty(uploadResults)){
                return FileStorageResult.FAIL();
            }
            String groupName = uploadResults[0];
            String remoteFileName = uploadResults[1];
            String filePath = MessageFormat.format("{0}/{1}", groupName, remoteFileName);
            log.debug("upload file successfully! filePath: {}", filePath);
            return FileStorageResult.SUCCESS(filePath);
        } catch (Exception e) {
            log.error("FastDFSClient storageClient.upload_file fail, error message: {}", e.getMessage(), e);
        }
        return FileStorageResult.FAIL();
    }

    /**
     * 删除文件
     *
     * @param filePathAndName
     */
    @Override
    public boolean deleteFile(String filePathAndName) {
        String[] file = parserFullPath(filePathAndName);
        try {
            storageClient.delete_file(file[0], file[1]);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("FastDFSClient storageClient.delete_file fail, filePath: {}, error message: {}", filePathAndName, e.getMessage(), e);
        }
        return Boolean.FALSE;
    }
}
