package com.xmomen.module.core.controller;

import com.xmomen.commons.Base64Utils;
import com.xmomen.framework.exception.BusinessException;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by tanxinzheng on 16/10/16.
 */
@Controller
public class FileUploadController {

    /**
     * 文件上传
     * @param file
     * @param username
     * @throws IOException
     */
    @RequestMapping(value = "/file/upload")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username ) throws IOException {

    }

    /**
     * 下载临时文件
     * @param filename
     * @param request
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    @RequestMapping(value = "/download/temps")
    public ResponseEntity download(@RequestParam("filename") String filename,
                         HttpServletRequest request) throws IOException, BusinessException {
        String realFilename = Base64Utils.decoder(filename);
        String filepath = request.getServletContext().getRealPath("/WEB-INF/temps/" + realFilename);
        File file = new File(filepath);
        if(!file.exists()) {
            throw new BusinessException("您要下载的文件的不存在");
        }
        String name = realFilename.substring(17, realFilename.length());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(name, "UTF-8"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        if(bytes != null){
            FileUtils.deleteQuietly(file);
        }
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }

}
