package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.QuestionDTO;
import com.project.jingmaoquan.model.Question;
import com.project.jingmaoquan.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;


    @RequestMapping("/")
    public String index(@RequestParam(name = "page" ,required = false,defaultValue = "1")Integer page,
                        @RequestParam(name = "size",required = false,defaultValue = "3")Integer size,
                        Model model){

        PaginationDTO<QuestionDTO> paginationDTO=questionService.list(page,size);
        model.addAttribute("pagination",paginationDTO);


        return "index";
    }
}
