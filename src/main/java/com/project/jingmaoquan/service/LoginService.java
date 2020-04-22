package com.project.jingmaoquan.service;

import com.project.jingmaoquan.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    int signUp(String username,String password,String phone);

    UserInfo login(String username, String password);
}
