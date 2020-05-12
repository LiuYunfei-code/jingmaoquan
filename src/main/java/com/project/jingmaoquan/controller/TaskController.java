package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.dto.CommentDTO;
import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.TaskDTO;
import com.project.jingmaoquan.model.Task;
import com.project.jingmaoquan.service.CommentService;
import com.project.jingmaoquan.service.TaskService;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private CommentService commentService;

    /**
     * 获取任务列表
     * @param page
     * @param size
     * @param model
     * @return
     */
    @RequestMapping("taskPart")
    public String taskPage(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                           @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                           Model model){
        // 获取任务列表
        PaginationDTO<TaskDTO> paginationDTO=taskService.list(page,size);
        model.addAttribute("pagination",paginationDTO);
        return "taskPart";
    }

    @RequestMapping("task")
    public String task(@RequestParam("id")Long taskId, Model model){
        // 查询信息
        TaskDTO taskDTO=taskService.findById(taskId);
        model.addAttribute("task",taskDTO);
        // 查询评论
        List<CommentDTO> commentDTOS = commentService.listByTargetId(taskId, 3);
        model.addAttribute("comments",commentDTOS);
        return "taskDetail";

    }
}
