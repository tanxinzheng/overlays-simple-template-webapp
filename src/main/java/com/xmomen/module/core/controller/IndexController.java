package com.xmomen.module.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tanxinzheng on 16/6/15.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "redirect:/index.html";
    }
}
