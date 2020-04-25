package com.project.jingmaoquan.service;

import com.project.jingmaoquan.dto.CommentCreateDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void insert(CommentCreateDTO commentCreateDTO,Long userId);
}
