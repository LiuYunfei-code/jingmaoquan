package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.dto.CommentCreateDTO;
import com.project.jingmaoquan.dto.CommentDTO;
import com.project.jingmaoquan.mapper.CommentMapper;
import com.project.jingmaoquan.mapper.QuestionExtMapper;
import com.project.jingmaoquan.mapper.UserInfoMapper;
import com.project.jingmaoquan.model.Comment;
import com.project.jingmaoquan.model.CommentExample;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.model.UserInfoExample;
import com.project.jingmaoquan.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void insert(CommentCreateDTO commentCreateDTO, Long userId) {
        // 创建 comment 对象
        Comment comment = new Comment();
        comment.setCommentatorId(userId);
        comment.setContent(commentCreateDTO.getContent());
        comment.setCreateTime(System.currentTimeMillis());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setParentType(commentCreateDTO.getParentType());
        // 保存数据
        commentMapper.insert(comment);
        // 将相应帖子的评论数加一
        if (commentCreateDTO.getParentType() == 1) {// 讨论帖
            questionExtMapper.incCommentCount(commentCreateDTO.getParentId());
        }
    }

    /**
     * 查询帖子的评论
     *
     * @param questionId
     * @param parentType
     * @return
     */
    @Override
    public List<CommentDTO> listByTargetId(Long questionId, Integer parentType) {
        // 根据 帖子id 查询评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(questionId).andParentTypeEqualTo(parentType);
        commentExample.setOrderByClause("create_time desc");
        List<Comment> comments = commentMapper.selectByExampleWithBLOBs(commentExample);
        // 查询评论人信息，并生成commentDTO 列表
        List<CommentDTO> commentDTOS = new ArrayList<>();
        UserInfoExample userInfoExample = new UserInfoExample();
        for (Comment comment : comments) {
            userInfoExample.createCriteria().andUserIdEqualTo(comment.getCommentatorId());
            List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setPhoto(userInfos.get(0).getPhoto());
            commentDTO.setUsername(userInfos.get(0).getUsername());
            commentDTOS.add(commentDTO);
        }

        return commentDTOS;


    }
}
