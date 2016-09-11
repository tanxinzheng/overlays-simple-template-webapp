package com.xmomen.module.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Jeng on 2016/1/5.
 */
@Controller
public class RegisterController {

    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    //@Autowired
    //UserService userService;

    /**
     * 用户注册
     * @param register          注册请求提交的表单对象
     * @param bindingResult     校验结果对象
     * @param request           http请求
     * @param model             领域对象
     * @return                  页面跳转地址
     */
//    @RequestMapping(value = "/register")
//    public String register(@ModelAttribute @Valid Register register,
//                           BindingResult bindingResult,
//                           HttpServletRequest request,
//                           Model model) {
//        if (!WebUtils.toHttp(request).getMethod().equalsIgnoreCase("POST")){
//            return "register";
//        }
//        if(!bindingResult.hasErrors()){
////            CreateUser user = new CreateUser();
////            user.setUsername(register.getUsername());
////            user.setPassword(register.getPassword());
////            SysUsers sysUsers = userService.createUser(user);
////            if(sysUsers.getId() != null && sysUsers.getId() > 0 ){
//                return "login";
//            }else{
//                logger.error("注册成功后返回的主键为空（或主键等于或小于0）");
//                model.addAttribute("error", "注册用户失败");
//                return "register";
//            }
//        }else{
//            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
//            return "register";
//        }
//    }

}
