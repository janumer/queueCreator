package com.queue.creator.service;

import com.queue.creator.model.EmployeeBean;
import com.queue.creator.xmlroot.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String queueName, Object messageObject) {
        if (messageObject instanceof String) {
            log.info("TextMessage to be send with queueName: {}", queueName);
            jmsTemplate.send(queueName, session -> session.createTextMessage((String) messageObject));
        } else if (messageObject instanceof Student) {
            log.info("Student Object to be send with queueName: {}", queueName);
            jmsTemplate.send(queueName, session -> session.createObjectMessage((Student) messageObject));
        } else if (messageObject instanceof EmployeeBean) {
            log.info("Employee Object to be send with queueName: {}", queueName);
            jmsTemplate.send(queueName, session -> session.createObjectMessage((EmployeeBean) messageObject));
        } else {
            throw new IllegalArgumentException("Unsupported message type");
        }
    }

}
