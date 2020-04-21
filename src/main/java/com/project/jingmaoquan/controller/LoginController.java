package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    /**
     * 跳转登录界面
     * @return
     */
    @GetMapping("loginPage")
    public String loginPage(){
        return "loginPage";
    }

    @GetMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        return "";
    }

    /**
     * 跳转注册界面
     * @return
     */
    @RequestMapping("signUpPage")
    public String signUpPage(){
        return "signUpPage";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping("signUp")
    public String signUp(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("phone") String phone, Model model){
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        model.addAttribute("phone",phone);
        // 非空校验
        if (StringUtils.isEmpty(username)){
            model.addAttribute("error","用户名不能为空");
            return "signUpPage";
        }else if (StringUtils.isEmpty(password)){
            model.addAttribute("error","密码不能为空");
            return "signUpPage";
        }else if (StringUtils.isEmpty(phone)){
            model.addAttribute("error","手机号不能为空");
            return "signUpPage";
        }
        // 保存用户数据，并判断用户名和手机号是否已经存在
        int state=loginService.signUp(username,password,phone);

        if (state==1){ // 用户名已存在
            model.addAttribute("error","用户名已存在");
            return "signUpPage";
        }else if (state==2){// 手机号已存在
            model.addAttribute("error","手机号注册");
            return "signUpPage";

        }else{ // 注册成功
            return "loginPage";

        }


    }


}
