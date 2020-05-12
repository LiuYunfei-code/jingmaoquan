package com.project.jingmaoquan.service;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.TaskDTO;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    PaginationDTO<TaskDTO> list(Integer page, Integer size);

    TaskDTO findById(Long taskId);
}
