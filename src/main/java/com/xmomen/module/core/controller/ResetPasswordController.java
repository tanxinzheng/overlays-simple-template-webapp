package com.xmomen.module.core.controller;

import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 重置密码控制器
 */
@Controller
public class ResetPasswordController {

    private static Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

    @Autowired
    AccountService accountService;

    /**
     * 找回密码
     * @param email
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/find_password")
    public String register(@RequestParam(value = "email") String email,
                           HttpServletRequest request,
                           Model model) {
        if(SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:/";
        }
        if (!WebUtils.toHttp(request).getMethod().equalsIgnoreCase("POST")){
            return "find_password";
        }
        if(StringUtils.trimToNull(email) == null){
            model.addAttribute("error", "注册用户失败");
            return "find_password";
        }else{
            model.addAttribute("error", "请输入电子邮箱");
            return "find_password";
        }
    }

}
