package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.QuestionDTO;
import com.project.jingmaoquan.model.Question;
import com.project.jingmaoquan.model.Second;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.service.QuestionService;
import com.project.jingmaoquan.service.SecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private SecondService secondService;

    @RequestMapping("profile")
    public String profilePage(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                              @RequestParam(value = "section", required = false, defaultValue = "1") Integer section, HttpServletRequest request, Model model) {
        // 获取 UserId
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        Long userId = userInfo.getUserId();
        if (section == 2) { // 我的讨论帖
            PaginationDTO<Question> paginationDTO = questionService.listByPublisherId(userId, page, size);
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("section", section);
        }else if (section==3){ // 我的二手贴
            PaginationDTO<Second> paginationDTO=secondService.listByPublisherId(userId,page,size);
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("section",section);

        }
        return "profile";
    }
}
