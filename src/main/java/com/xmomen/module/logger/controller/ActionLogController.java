package com.xmomen.module.logger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 17/8/5.
 */
@RestController
@RequestMapping(value = "/action_log")
public class ActionLogController {

    /**
     * description
     * @param   String
     * @return  string
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String methodName(@RequestParam(value = "param") String param){
        return null;
    }
}
