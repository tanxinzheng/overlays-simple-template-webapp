package com.xmomen.module.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

        byte[] bytes;

        if (!file.isEmpty()) {
            bytes = file.getBytes();
            //store file in storage
        }

        System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), username));
    }

}
