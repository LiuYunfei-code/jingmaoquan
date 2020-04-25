package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.QuestionDTO;
import com.project.jingmaoquan.mapper.QuestionExtMapper;
import com.project.jingmaoquan.mapper.QuestionMapper;
import com.project.jingmaoquan.mapper.UserInfoMapper;
import com.project.jingmaoquan.model.Question;
import com.project.jingmaoquan.model.QuestionExample;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.model.UserInfoExample;
import com.project.jingmaoquan.service.QuestionService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 分页获取讨论区帖子列表
     * @param page
     * @param size
     * @return
     */
    @Override
    public PaginationDTO<QuestionDTO> list(Integer page, Integer size) {

        int offset = size * (page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("publish_time desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));

        // 将用户名和question拼接
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andUserIdEqualTo(question.getPublisherId());
            List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUsername(userInfos.get(0).getUsername());
            questionDTOS.add(questionDTO);
        }

        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        paginationDTO.setData(questionDTOS);

        //获取总记录数
        Long totalCount=questionMapper.countByExample(new QuestionExample());
        // 设置分页
        paginationDTO.setPagination(totalCount,page,size);


        return paginationDTO;

    }

    /**
     * 查看讨论帖详情
     * @param questionId
     * @return
     */
    @Override
    public QuestionDTO findById(Long questionId) {
        // 根据问题 ID 查询
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andQuestionIdEqualTo(questionId);
        List<Question> questions=questionMapper.selectByExampleWithBLOBs(questionExample);
        // 根据用户 publish_id 查询用户信息
        UserInfoExample userInfoExample=new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(questions.get(0).getPublisherId());
        List<UserInfo> userInfos=userInfoMapper.selectByExample(userInfoExample);

        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(questions.get(0),questionDTO);
        questionDTO.setUsername(userInfos.get(0).getUsername());
        return questionDTO;
    }

    /**
     * 增加阅读数
     * @param questionId
     */
    @Override
    public void incView(Long questionId) {
        questionExtMapper.incView(questionId);
    }

    @Override
    public void create(String title, String content, Long publisherId) {
        // 创建 question 对象
        Question question=new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setPublisherId(publisherId);
        question.setPublishTime(System.currentTimeMillis());
        question.setCommentCount(0);
        question.setViewCount(0);
        // 插入数据库
        questionMapper.insert(question);

    }
}
