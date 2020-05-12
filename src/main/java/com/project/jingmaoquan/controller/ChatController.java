package com.project.jingmaoquan.controller;

import com.alibaba.fastjson.JSONObject;
import com.project.jingmaoquan.model.Message;
import com.project.jingmaoquan.model.RequestMessage;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;


    private final SimpMessagingTemplate template;

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    /**
     * 跳转到聊天界面
     * @param receiver 消息接受人的 id
     * @param model
     * @return
     */
    @RequestMapping("chatPage")
    public String chatPage(@RequestParam("receiver")Long receiver, Model model){
        model.addAttribute("receiver",receiver);
        return "chat";
    }

    /**
     * 发送消息,并将消息记录保存到数据库
     * @param message 消息的详细信息
     */
    @MessageMapping("/chat")
    public void greeting(RequestMessage message) {
        // 获取当前时间作为消息发送时间
        Long time=System.currentTimeMillis();

        // 发送消息
        this.template.convertAndSend("/msg/"+message.getReceiver(), JSONObject.parse("{content:'"+ HtmlUtils.htmlEscape(message.getMsg())+"'}"));

        // 保存消息到数据库
        Long sender=message.getSender();
        Long receiver=message.getReceiver();
        String content=message.getMsg();
        messageService.save(sender,receiver,content,time);


    }
}
