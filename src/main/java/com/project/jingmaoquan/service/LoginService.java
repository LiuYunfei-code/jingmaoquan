package com.project.jingmaoquan.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    int signUp(String username,String password,String phone);
}
