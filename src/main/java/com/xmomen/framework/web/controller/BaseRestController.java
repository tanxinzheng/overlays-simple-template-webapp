package com.xmomen.framework.web.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 17/6/12.
 */
@RestController
public class BaseRestController {

    /**
     * 获取当前用户ID
     * @return
     */
    public String getCurrentUserId(){
        return "ADMIN";
    }
}
