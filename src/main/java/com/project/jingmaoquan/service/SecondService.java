package com.project.jingmaoquan.service;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.SecondDTO;
import com.project.jingmaoquan.model.Second;
import com.project.jingmaoquan.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface SecondService {
    PaginationDTO<SecondDTO> list(Integer page, Integer size);

    SecondDTO findBySecondId(Long secondId);

    void update(String name, String price, String detail,Long secondId);

    Long create(String name, String price, String detail, UserInfo userInfo);

    PaginationDTO<Second> listByPublisherId(Long userId, Integer page, Integer size);

    void delete(Long secondId);

    void sold(Long secondId);
}
