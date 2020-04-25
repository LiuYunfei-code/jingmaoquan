package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.dto.CommentCreateDTO;
import com.project.jingmaoquan.mapper.CommentMapper;
import com.project.jingmaoquan.mapper.QuestionExtMapper;
import com.project.jingmaoquan.model.Comment;
import com.project.jingmaoquan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Override
    public void insert(CommentCreateDTO commentCreateDTO,Long userId) {
        // 创建 comment 对象
        Comment comment=new Comment();
        comment.setCommentatorId(userId);
        comment.setContent(commentCreateDTO.getContent());
        comment.setCreateTime(System.currentTimeMillis());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setParentType(commentCreateDTO.getParentType());
        // 保存数据
        commentMapper.insert(comment);
        // 将相应帖子的评论数加一
        if (commentCreateDTO.getParentType()==1){// 讨论帖
            questionExtMapper.incCommentCount(commentCreateDTO.getParentId());
        }
    }
}
