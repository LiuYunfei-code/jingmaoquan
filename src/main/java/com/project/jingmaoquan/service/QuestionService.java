package com.project.jingmaoquan.service;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.QuestionDTO;
import com.project.jingmaoquan.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    PaginationDTO<QuestionDTO> list(Integer page, Integer size);
}
