package com.queue.creator.controller;

import com.queue.creator.model.EmployeeBean;
import com.queue.creator.model.MessageBean;
import com.queue.creator.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    return "Sending message to Queue Name: "+messageBean.getQueueName()+" with Message: "+messageBean.getMessage();
    }

    @PostMapping("/registration/{queueName}")
    public String registration(@PathVariable String queueName, @RequestBody EmployeeBean messageBean){
        messageSender.send(queueName, messageBean);
        return "Sending message to Queue Name: "+queueName+" with Message: "+messageBean;
    }
}
