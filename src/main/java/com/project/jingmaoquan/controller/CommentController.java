package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.dto.CommentCreateDTO;
import com.project.jingmaoquan.dto.ResultDTO;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "comment", method = RequestMethod.POST)
    @ResponseBody
    public Object comment(@RequestBody CommentCreateDTO commentCreateDTO,
                          HttpServletRequest request) {


        // 检查评论内容是否为空
        if (commentCreateDTO == null || StringUtils.isEmpty(commentCreateDTO.getContent())) {

        } else {
            // 获取评论者 ID
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
            Long userId = userInfo.getUserId();

            // 保存数据
            commentService.insert(commentCreateDTO, userId);

//            logger.info(commentCreateDTO.toString());
        }

        return ResultDTO.okOf();


    }
}
