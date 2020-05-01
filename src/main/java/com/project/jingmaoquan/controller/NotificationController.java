package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.dto.NotificationDTO;
import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.model.Notification;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @RequestMapping("notification")
    public String notification(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                               @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                               HttpServletRequest request,Model model){
        // 获取 UserID
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute("user");
        // 查询通知列表
        PaginationDTO<NotificationDTO> paginationDTO=notificationService.list(userInfo.getUserId(),page,size);
        model.addAttribute("pagination",paginationDTO);
        model.addAttribute("section",1);
        return "profile";
    }
}
