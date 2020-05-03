package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.dto.CommentDTO;
import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.SecondDTO;
import com.project.jingmaoquan.model.SecondWithBLOBs;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.service.CommentService;
import com.project.jingmaoquan.service.SecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SecondController {

    @Autowired
    private SecondService secondService;
    @Autowired
    private CommentService commentService;


    /**
     * 跳转二手区页面
     * @param page
     * @param size
     * @param device
     * @param model
     * @return
     */
    @RequestMapping("secondPart")
    public String secondPage(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                             @RequestParam(name = "device", required = false) Integer device,
                             Model model) {
        PaginationDTO<SecondDTO> paginationDTO = secondService.list(page, size);
        model.addAttribute("pagination", paginationDTO);

        if (device!=null&&device == 1) {
            return "secondPart-m";
        } else {
            return "secondPart";
        }
    }

    /**
     * 查看二手贴详情
     * @param secondId
     * @param model
     * @return
     */
    @RequestMapping("second")
    public String second(@RequestParam("id")Long secondId,Model model){
        // 查询帖子信息
        SecondDTO secondDTO=secondService.findBySecondId(secondId);
        model.addAttribute("second",secondDTO);

        // 增加阅读数

        // 查询评论
        List<CommentDTO> commentDTOS = commentService.listByTargetId(secondId, 2);
        model.addAttribute("comments",commentDTOS);

        return "secondDetail";

    }
    @RequestMapping("second/publishPage")
    public String publishPage(@RequestParam(value = "id",required = false)Long secondId,Model model){
        if (secondId!=null){
            SecondDTO secondDTO = secondService.findBySecondId(secondId);
            model.addAttribute("second",secondDTO);
        }
        return "publishSecond";
    }
    @RequestMapping("second/publish")
    public String publish(@RequestParam("name")String name,
                          @RequestParam("price")String price,
                          @RequestParam("detail")String detail,
                          @RequestParam(value = "id",required = false)Long secondId,
                          HttpServletRequest request,Model model){
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute("user");

        SecondWithBLOBs second=new SecondWithBLOBs();
        second.setSecondId(secondId);
        second.setName(name);
        second.setPrice(price);
        second.setDetail(detail);
        model.addAttribute("second",second);

        // 判断非空
        if (StringUtils.isEmpty(name)){
            model.addAttribute("error","标题不能为空");

            return "publishSecond";
        }else if(StringUtils.isEmpty(detail)){
            model.addAttribute("error","详细描述不能为空");

            return "publishSecond";
        }

        if (secondId!=null){ // 编辑
            secondService.update(name,price,detail,secondId);
        }else{ // 新发布
            secondId=secondService.create(name,price,detail,userInfo);
        }
        return "redirect:/second?id="+secondId;

    }

}
