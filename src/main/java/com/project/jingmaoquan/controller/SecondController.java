package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.dto.*;
import com.project.jingmaoquan.mapper.SecondMapper;
import com.project.jingmaoquan.model.*;
import com.project.jingmaoquan.service.CommentService;
import com.project.jingmaoquan.service.SecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SecondController {

    @Autowired
    private SecondService secondService;
    @Autowired
    private SecondMapper secondMapper;
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
    /**
     * 删除二手帖
     *
     * @param secondRequestDTO
     * @return
     */
    @RequestMapping(value = "second/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestBody SecondRequestDTO secondRequestDTO, HttpServletRequest request) {

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        // 查询帖子发布人的id
        SecondExample secondExample=new SecondExample();
        secondExample.createCriteria().andSecondIdEqualTo(secondRequestDTO.getId());
        List<Second> seconds = secondMapper.selectByExample(secondExample);
        Long publisherId = seconds.get(0).getPublisherId();

        if (userInfo == null) { // 未登录
        } else if (publisherId.equals(userInfo.getUserId())) { // 要删除的帖子的发布人和当前用户id相同才能删除
            secondService.delete(secondRequestDTO.getId());
        }
        return ResultDTO.okOf();
    }

    /**
     * 标记为已售
     *
     * @param secondRequestDTO
     * @return
     */
    @RequestMapping(value = "second/sold", method = RequestMethod.POST)
    @ResponseBody
    public Object sold(@RequestBody SecondRequestDTO secondRequestDTO, HttpServletRequest request) {

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        // 查询帖子发布人的id
        SecondExample secondExample=new SecondExample();
        secondExample.createCriteria().andSecondIdEqualTo(secondRequestDTO.getId());
        List<Second> seconds = secondMapper.selectByExample(secondExample);
        Long publisherId = seconds.get(0).getPublisherId();

        if (userInfo == null) { // 未登录
        } else if (publisherId.equals(userInfo.getUserId())) { // 要标记的帖子的发布人和当前用户id相同才能标记
            secondService.sold(secondRequestDTO.getId());
        }
        return ResultDTO.okOf();
    }
}
