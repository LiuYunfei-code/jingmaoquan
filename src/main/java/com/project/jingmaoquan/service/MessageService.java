package com.project.jingmaoquan.service;

import org.springframework.stereotype.Service;

@Service
public interface MessageService {

    void save(Long sender, Long receiver, String content, Long time);
}
