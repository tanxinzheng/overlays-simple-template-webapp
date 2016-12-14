package com.xmomen.module.core.controller;

import com.xmomen.module.core.model.DemoModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 16/12/11.
 */
@RestController
public class DemoController {

    /**
     * 用户设置
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public DemoModel test(@RequestBody DemoModel demoModel){
        return demoModel;
    }
}
