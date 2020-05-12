package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.dto.CommentCreateDTO;
import com.project.jingmaoquan.dto.NotificationDTO;
import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.mapper.*;
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
    @Autowired
    private SecondMapper secondMapper;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public PaginationDTO<NotificationDTO> list(Long userId, Integer page, Integer size) {
        int offset = size * (page - 1);

        // 查询通知
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverIdEqualTo(userId);
        notificationExample.setOrderByClause("create_time desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(notificationExample, new RowBounds(offset, size));

        List<NotificationDTO> notificationDTOList = new ArrayList<>();

        for (Notification notification : notifications) {
//            if (notification.getArticleType()==1){ // 讨论帖
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);


            // 查询评论的内容
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria().andIdEqualTo(notification.getCommentId());
            List<CommentWithBLOBs> comments = commentMapper.selectByExampleWithBLOBs(commentExample);
            notificationDTO.setContent(comments.get(0).getContent());


            if (comments.get(0).getParentType() == 1) { // 讨论帖
                // 查询标题
                QuestionExample questionExample = new QuestionExample();
                questionExample.createCriteria().andQuestionIdEqualTo(notification.getArticleId());
                List<Question> questions = questionMapper.selectByExampleWithBLOBs(questionExample);
                notificationDTO.setArticleTitle(questions.get(0).getTitle());

                // 设置描述
                if (comments.get(0).getType() == 1) {
                    notificationDTO.setNotifyDesc("评论了你的讨论帖");
                } else if (comments.get(0).getType() == 2) {
                    notificationDTO.setNotifyDesc("回复了你在讨论帖中的评论");
                }
            } else if (comments.get(0).getParentType() == 2) { // 二手贴
                // 查询标题
                SecondExample secondExample = new SecondExample();
                secondExample.createCriteria().andSecondIdEqualTo(notification.getArticleId());
                List<SecondWithBLOBs> secondWithBLOBs = secondMapper.selectByExampleWithBLOBs(secondExample);
                notificationDTO.setArticleTitle(secondWithBLOBs.get(0).getName());
                // 设置描述
                if (comments.get(0).getType() == 1) {
                    notificationDTO.setNotifyDesc("评论了你的二手帖");
                } else if (comments.get(0).getType() == 2) {
                    notificationDTO.setNotifyDesc("回复了你在二手贴中的评论");
                }
            }else if (comments.get(0).getParentType() == 3) {// 任务贴
                // 查询标题
                TaskExample taskExample = new TaskExample();
                taskExample.createCriteria().andTaskIdEqualTo(notification.getArticleId());
                List<Task> tasks =taskMapper.selectByExampleWithBLOBs(taskExample);
                notificationDTO.setArticleTitle(tasks.get(0).getTaskName());
                // 设置描述
                if (comments.get(0).getType() == 1) {
                    notificationDTO.setNotifyDesc("评论了你的任务帖");
                } else if (comments.get(0).getType() == 2) {
                    notificationDTO.setNotifyDesc("回复了你在任务贴中的评论");
                }
            }
            notificationDTOList.add(notificationDTO);

        }
//        }

        // 设置分页
        Long totalCount = notificationMapper.countByExample(notificationExample);
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        paginationDTO.setData(notificationDTOList);
        paginationDTO.setPagination(totalCount, page, size);

        return paginationDTO;
    }

    /**
     * 创建评论的通知
     *
     * @param commentCreateDTO
     * @param userInfo
     * @param comment
     */
    @Override
    public void createCommentNotification(CommentCreateDTO commentCreateDTO, UserInfo userInfo, Comment comment) {

        Notification notification = new Notification();
        notification.setNotifierId(userInfo.getUserId());
        notification.setNotifierUsername(userInfo.getUsername());
        notification.setArticleId(commentCreateDTO.getArticleId());
        notification.setReceiverId(commentCreateDTO.getParentUserId());
        notification.setCommentId(comment.getId());
        notification.setState(0);
        notification.setCreateTime(System.currentTimeMillis());
        notification.setArticleType(commentCreateDTO.getParentType());

        notificationMapper.insertSelective(notification);
    }

    /**
     * 标记为已读
     * @param notificationId
     */
    @Override
    public void haveRead(Long notificationId) {
        Notification notification=new Notification();
        notification.setId(notificationId);
        notification.setState(1);

        notificationMapper.updateByPrimaryKeySelective(notification);
    }

}
