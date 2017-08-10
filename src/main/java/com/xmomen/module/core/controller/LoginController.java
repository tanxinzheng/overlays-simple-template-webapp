package com.xmomen.module.core.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jeng on 2016/1/5.
 */
@Controller
public class LoginController {

    /**
     * 用户登陆页面
     * @param request   http请求
     * @param model     领域对象
     * @return          页面跳转地址
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model){
        if(SecurityUtils.getSubject().isAuthenticated()){
            return "forward:/";
        }
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "此帐号未注册。";
        } else if(LockedAccountException.class.getName().equals(exceptionClassName)) {
            error = "此帐号已被冻结，请联系客服。";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名或密码错误。";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
            error = "已超过重试次数，请10分钟后重新操作。";
        }else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }

//    @ResponseBody
//    @RequestMapping(value = "/login/token",method = RequestMethod.POST)
//    public String login(@RequestParam(value = "username") String username,
//                                @RequestParam(value = "password") String password){
//        Key key = MacProvider.generateKey();
//        String compactJws = Jwts.builder()
//                .setSubject(username)
//                .signWith(SignatureAlgorithm.HS512, key)
//                .compact();
//        return null;
//    }

}
