package com.project.jingmaoquan.service;

import com.project.jingmaoquan.dto.CommentCreateDTO;
import com.project.jingmaoquan.dto.CommentDTO;
import com.project.jingmaoquan.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Comment insert(CommentCreateDTO commentCreateDTO, Long userId);

    List<CommentDTO> listByTargetId(Long questionId,Integer parentType);

    List<CommentDTO> listSubComment(Long commentId);
}
