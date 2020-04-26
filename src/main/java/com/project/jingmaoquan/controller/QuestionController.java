package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.dto.CommentDTO;
import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.QuestionDTO;
import com.project.jingmaoquan.mapper.QuestionMapper;
import com.project.jingmaoquan.model.Question;
import com.project.jingmaoquan.model.QuestionExample;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.service.CommentService;
import com.project.jingmaoquan.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {
    final Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private QuestionMapper questionMapper;


    @RequestMapping("/")
    public String index(@RequestParam(name = "page" ,required = false,defaultValue = "1")Integer page,
                        @RequestParam(name = "size",required = false,defaultValue = "10")Integer size,
                        Model model){

        PaginationDTO<QuestionDTO> paginationDTO=questionService.list(page,size);
        model.addAttribute("pagination",paginationDTO);


        return "questionPart";
    }

    /**
     * 查看讨论帖详情
     * @param questionId
     * @param model
     * @return
     */
    @RequestMapping("question")
    public String questionDetail(@RequestParam("id") Long questionId,Model model){
        // 查询帖子信息
        QuestionDTO questionDTO=questionService.findById(questionId);
        model.addAttribute("question",questionDTO);
        // 增加阅读数
        questionService.incView(questionId);

        // 查询评论
        List<CommentDTO> commentDTOs=commentService.listByTargetId(questionId,1);
        model.addAttribute("comments",commentDTOs);

        return "questionDetail";
    }

    @RequestMapping("question/publishPage")
    public String publishPage(@RequestParam(value = "id",required = false)Long questionId,Model model){
        if (questionId!=null){ // 编辑帖子
            QuestionExample questionExample=new QuestionExample();
            questionExample.createCriteria().andQuestionIdEqualTo(questionId);
            List<Question> questions=questionMapper.selectByExampleWithBLOBs(questionExample);
            model.addAttribute("question",questions.get(0));
        }

        return "publishQuestion";
    }

    /**
     * 发布或编辑讨论帖
     * @param content
     * @return
     */
    @RequestMapping("question/publish")
    public String publish(@RequestParam("content")String content,
                          @RequestParam("title")String title,
                          @RequestParam(value = "id",required = false)Long questionId,
                          HttpServletRequest request){
        if (questionId!=null){ // 编辑
            questionService.update(questionId,title,content);
        }else { // 发布新帖
            UserInfo publisher = (UserInfo) request.getSession().getAttribute("user");
            Long publisherId = publisher.getUserId();
            questionService.create(title, content, publisherId);
        }
        return "redirect:/";
    }

}
