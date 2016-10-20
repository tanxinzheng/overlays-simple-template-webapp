package com.xmomen.module.core.controller;

import com.xmomen.module.core.model.AccountModel;
import com.xmomen.module.core.model.Register;
import com.xmomen.module.core.service.AccountService;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Jeng on 2016/1/5.
 */
@Controller
public class RegisterController {

    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

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
    @RequestMapping(value = "/register")
    public String register(@ModelAttribute @Valid Register register,
                           BindingResult bindingResult,
                           HttpServletRequest request,
                           Model model) {
        if (!WebUtils.toHttp(request).getMethod().equalsIgnoreCase("POST")){
            return "register";
        }
        if(!bindingResult.hasErrors()){
            AccountModel accountModel = accountService.register(register);
            if(accountModel != null){
                return "login";
            }else{
                model.addAttribute("error", "注册用户失败");
                return "register";
            }
        }else{
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "register";
        }
    }

}
