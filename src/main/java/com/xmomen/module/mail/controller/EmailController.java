package com.xmomen.module.mail.controller;

import com.xmomen.framework.web.rest.RestResult;
import com.xmomen.module.mail.model.EmailModel;
import com.xmomen.module.mail.service.EmailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by tanxinzheng on 2018/6/10.
 */
@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Resource
    private EmailService emailService;

    /**
     * 测试邮件发送
     */
    @ApiOperation(value="测试邮件发送", notes="getEntityById")
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    RestResult sendEmail(@RequestBody @Valid EmailModel emailModel) {
        emailService.sendEmail(emailModel);
        return RestResult.OK("邮件发送成功");
    }
}
