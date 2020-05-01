package com.project.jingmaoquan.service;

import com.project.jingmaoquan.dto.CommentCreateDTO;
import com.project.jingmaoquan.dto.NotificationDTO;
import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.model.Comment;
import com.project.jingmaoquan.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    PaginationDTO<NotificationDTO> list(Long userId,Integer page,Integer size);


    void createCommentNotification(CommentCreateDTO commentCreateDTO, UserInfo userInfo, Comment comment);
}
