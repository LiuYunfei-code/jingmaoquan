package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.QuestionDTO;
import com.project.jingmaoquan.mapper.QuestionMapper;
import com.project.jingmaoquan.mapper.UserInfoMapper;
import com.project.jingmaoquan.model.Question;
import com.project.jingmaoquan.model.QuestionExample;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.model.UserInfoExample;
import com.project.jingmaoquan.service.QuestionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public PaginationDTO<QuestionDTO> list(Integer page, Integer size) {

        Integer offset = size * (page - 1);
        QuestionExample questionExample = new QuestionExample();
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
}
