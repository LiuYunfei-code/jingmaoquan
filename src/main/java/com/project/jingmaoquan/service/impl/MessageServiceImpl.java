package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.mapper.MessageMapper;
import com.project.jingmaoquan.model.Message;
import com.project.jingmaoquan.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public void save(Long sender, Long receiver, String content, Long time) {
        Message message=new Message();
        message.setSenderId(sender);
        message.setReceiverId(receiver);
        message.setContent(content);
        message.setTime(time);
        messageMapper.insertSelective(message);
    }
}
