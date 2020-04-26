package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 跳转登录界面
     * @param type
     * @param questionId
     * @param model
     * @return
     */
    @GetMapping("loginPage")
    public String loginPage(@RequestParam(value = "type",required = false)Integer type,
                            @RequestParam(value = "id",required = false)Long questionId,Model model) {
        if (type!=null&&type==1){// 讨论帖
            model.addAttribute("url","/question?id="+questionId);
        }
        return "loginPage";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "url",required = false)String url,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("user",url);

        if (StringUtils.isEmpty(username)) {
            model.addAttribute("error", "用户名不能为空");
            return "loginPage";
        }
        if (StringUtils.isEmpty(password)) {
            model.addAttribute("error", "密码不能为空");
            return "loginPage";
        }
        UserInfo userInfo = loginService.login(username, password);

        if (userInfo == null) { // 登录失败
            model.addAttribute("error", "用户名或密码错误");
            return "loginPage";
        } else { // 登录成功
            request.getSession().setAttribute("user", userInfo);
            // 设置 Cookie
            Cookie cookie = new Cookie("token", userInfo.getToken());
            // 设置 Cookie 过期时间,单位秒
            cookie.setMaxAge(60 * 60 * 24 * 30); // 一个月
            response.addCookie(cookie);

            if (!StringUtils.isEmpty(url)){
                return "redirect:"+url;
            }else {
                return "redirect:/";
            }

        }
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";

    }

    /**
     * 跳转注册界面
     *
     * @return
     */
    @RequestMapping("signUpPage")
    public String signUpPage() {
        return "signUpPage";
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping("signUp")
    public String signUp(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("phone") String phone, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("phone", phone);
        // 非空校验
        if (StringUtils.isEmpty(username)) {
            model.addAttribute("error", "用户名不能为空");
            return "signUpPage";
        } else if (StringUtils.isEmpty(password)) {
            model.addAttribute("error", "密码不能为空");
            return "signUpPage";
        } else if (StringUtils.isEmpty(phone)) {
            model.addAttribute("error", "手机号不能为空");
            return "signUpPage";
        }
        // 保存用户数据，并判断用户名和手机号是否已经存在
        int state = loginService.signUp(username, password, phone);

        if (state == 1) { // 用户名已存在
            model.addAttribute("error", "用户名已存在");
            return "signUpPage";
        } else if (state == 2) {// 手机号已存在
            model.addAttribute("error", "手机号注册");
            return "signUpPage";

        } else { // 注册成功
            return "loginPage";

        }


    }


}
