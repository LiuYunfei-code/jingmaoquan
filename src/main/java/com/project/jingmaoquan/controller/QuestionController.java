package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.QuestionDTO;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {
    final Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private QuestionService questionService;


    @RequestMapping("/")
    public String index(@RequestParam(name = "page" ,required = false,defaultValue = "1")Integer page,
                        @RequestParam(name = "size",required = false,defaultValue = "10")Integer size,
                        Model model){

        PaginationDTO<QuestionDTO> paginationDTO=questionService.list(page,size);
        model.addAttribute("pagination",paginationDTO);


        return "questionPart";
    }

    /**
     * 查看讨论帖详情，增加帖子浏览量
     * @param questionId
     * @param model
     * @return
     */
    @RequestMapping("question")
    public String questionDetail(@RequestParam("id") Long questionId,Model model){
        // 查询帖子信息
        QuestionDTO questionDTO=questionService.findById(questionId);
        // 增加阅读数
        questionService.incView(questionId);

        model.addAttribute("question",questionDTO);
        return "questionDetail";
    }

    @RequestMapping("question/publishPage")
    public String publishPage(){
        return "publishQuestion";
    }

    /**
     * 发布讨论帖
     * @param content
     * @return
     */
    @RequestMapping("question/publish")
    public String publish(@RequestParam("content")String content,
                          @RequestParam("title")String title,
                          HttpServletRequest request){
        UserInfo publisher= (UserInfo) request.getSession().getAttribute("user");
        Long publisherId=publisher.getUserId();
        questionService.create(title,content,publisherId);
        return "redirect:/";
    }

}
