package com.queue.creator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate, MessageConverter messageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplate.setMessageConverter(messageConverter);
    }

    public void send(String queueName, Object message){
        jmsTemplate.convertAndSend(queueName, message);
        log.info("Message has been send with queueName: {}", queueName);
    }
}
