package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.dto.CommentCreateDTO;
import com.project.jingmaoquan.dto.NotificationDTO;
import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.mapper.CommentMapper;
import com.project.jingmaoquan.mapper.NotificationMapper;
import com.project.jingmaoquan.mapper.QuestionMapper;
import com.project.jingmaoquan.model.*;
import com.project.jingmaoquan.service.NotificationService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public PaginationDTO<NotificationDTO> list(Long userId, Integer page, Integer size) {
        int offset = size * (page - 1);

        // 查询通知
        NotificationExample notificationExample=new NotificationExample();
        notificationExample.createCriteria().andReceiverIdEqualTo(userId);
        List<Notification> notifications=notificationMapper.selectByExampleWithRowbounds(notificationExample,new RowBounds(offset,size));

        List<NotificationDTO> notificationDTOList=new ArrayList<>();

        for (Notification notification : notifications) {
            if (notification.getArticleType()==1){ // 讨论帖
                NotificationDTO notificationDTO=new NotificationDTO();
                BeanUtils.copyProperties(notification,notificationDTO);
                // 查询标题
                QuestionExample questionExample=new QuestionExample();
                questionExample.createCriteria().andQuestionIdEqualTo(notification.getArticleId());
                List<Question> questions=questionMapper.selectByExampleWithBLOBs(questionExample);
                notificationDTO.setArticleTitle(questions.get(0).getTitle());

                // 查询评论的内容
                CommentExample commentExample=new CommentExample();
                commentExample.createCriteria().andIdEqualTo(notification.getCommentId());
                List<CommentWithBLOBs> comments=commentMapper.selectByExampleWithBLOBs(commentExample);
                notificationDTO.setContent(comments.get(0).getContent());

                // 设置描述
                if (comments.get(0).getType()==1){
                    notificationDTO.setNotifyDesc("回复了你的讨论帖");
                }else if (comments.get(0).getType()==2){
                    notificationDTO.setNotifyDesc("回复了你在讨论帖中的评论");
                }

                notificationDTOList.add(notificationDTO);

            }
        }

        // 设置分页
        Long totalCount=notificationMapper.countByExample(notificationExample);
        PaginationDTO<NotificationDTO> paginationDTO=new PaginationDTO<>();
        paginationDTO.setData(notificationDTOList);
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }

    /**
     * 创建评论的通知
     * @param commentCreateDTO
     * @param userInfo
     * @param comment
     */
    @Override
    public void createCommentNotification(CommentCreateDTO commentCreateDTO, UserInfo userInfo, Comment comment) {

        Notification notification=new Notification();
        notification.setNotifierId(userInfo.getUserId());
        notification.setNotifierUsername(userInfo.getUsername());
        notification.setArticleId(commentCreateDTO.getArticleId());
        notification.setReceiverId(commentCreateDTO.getParentUserId());
        notification.setCommentId(comment.getId());
        notification.setState(1);
        notification.setCreateTime(System.currentTimeMillis());
        notification.setArticleType(commentCreateDTO.getParentType());

        notificationMapper.insertSelective(notification);
    }

}
