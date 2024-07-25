package com.queue.creator.controller;

import com.queue.creator.model.MessageBean;
import com.queue.creator.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    MessageSender messageSender;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageBean messageBean){
    messageSender.send(messageBean.getQueueName(), messageBean.getMessage());
    return "Message sent to queue: "+messageBean.getQueueName();
    }
}
