package com.queue.creator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String queueName, String message){
        jmsTemplate.convertAndSend(queueName, message);
        log.info("Message has been send with queueName: {}", queueName);
    }
}
