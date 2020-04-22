package com.project.jingmaoquan.interceptor;

import com.project.jingmaoquan.mapper.UserInfoMapper;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.model.UserInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserInfoExample userInfoExample=new UserInfoExample();
                    userInfoExample.createCriteria().andTokenEqualTo(token);
                    List<UserInfo> users = userInfoMapper.selectByExample(userInfoExample);
                    if (users.size() != 0) {
                        request.getSession().setAttribute("user", users.get(0));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
