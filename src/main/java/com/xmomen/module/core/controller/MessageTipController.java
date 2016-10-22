package com.xmomen.module.core.controller;

import com.xmomen.module.core.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Jeng on 2016/1/5.
 */
@Controller
public class MessageTipController {

    private static Logger logger = LoggerFactory.getLogger(MessageTipController.class);

    @Autowired
    AccountService accountService;

    /**
     * 用户注册
     * @param register          注册请求提交的表单对象
     * @param bindingResult     校验结果对象
     * @param request           http请求
     * @param model             领域对象
     * @return                  页面跳转地址
     */
    @RequestMapping(value = "/{action}/message")
    public String register(@PathVariable(value = "action") String action,
                           Model model,
                           HttpServletRequest request) {
        if(SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:/";
        }
        if(action.equals("register")){
            // 已登录则直接跳入首页
            model.addAttribute("message", "恭喜您已注册成功。");
            model.addAttribute("action", action);
        }
        return "message";
    }

}
