package com.xmomen.framework.fss.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tanxinzheng on 2018/7/22.
 */
@Data
public class FileStorageInfo {

    private String fileName;
    private byte[] content;
    private String fileExt;
    private long fileSize;
    private String contentType;
    private FileStorageNameValuePair[] mateList;

    public FileStorageInfo() {
    }

    public FileStorageInfo(MultipartFile multipartFile) {
        try {
            this.content = multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String originalFileName = multipartFile.getOriginalFilename();
        this.fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        this.fileName = multipartFile.getName();
        this.fileSize = multipartFile.getSize();
        this.contentType = multipartFile.getContentType();
    }

    public FileStorageInfo(String fileExt, InputStream inputStream) throws IOException {
        this.fileExt = fileExt;
        if(inputStream != null){
            int len1 = inputStream.available();
            content = new byte[len1];
            inputStream.read(content);
        }
        inputStream.close();
    }

    public InputStream getInputStream(){
        if(content == null){
            return null;
        }
        return new ByteArrayInputStream(content);
    }

    public String getFullPath(){
        return fileName + File.pathSeparator + fileExt;
    }

    public String[] parserFullPath(String fullPath){
        return fullPath.split(File.separator);
    }

    @Data
    public static class FileStorageNameValuePair {
        private String name;
        private String value;
    }

}
