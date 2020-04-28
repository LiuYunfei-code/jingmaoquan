package com.project.jingmaoquan.service;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.QuestionDTO;
import com.project.jingmaoquan.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    PaginationDTO<QuestionDTO> list(Integer page, Integer size);

    QuestionDTO findById(Long questionId);

    /**
     * 增加阅读数
     * @param questionId
     */
    void incView(Long questionId);

    void create(String title, String content, Long publisherId);

    void update(Long questionId, String title, String content);

    PaginationDTO<Question> listByPublisherId(Long userId, Integer page, Integer size);

    void delete(Long questionId);
}
