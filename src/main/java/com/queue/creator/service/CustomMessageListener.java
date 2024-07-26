package com.queue.creator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
@Slf4j
public class CustomMessageListener implements MessageListener {

@Override
public void onMessage(Message message) {
    try {
        if (message instanceof TextMessage) {
            String textMessage = ((TextMessage) message).getText();
            log.info("--> Received Text Message: {}", textMessage);
        } else if (message instanceof ObjectMessage) {
            Object objectMessage = ((ObjectMessage) message).getObject();
            log.info("--> Received Object Message: {}", objectMessage);
        } else {
            log.warn("Received unsupported message type");
        }
    } catch (JMSException e) {
        log.error("JMSException occurred while processing message: {}", e.getMessage());
    } catch (RuntimeException e) {
        log.error("RuntimeException occurred while processing message: {}", e.getMessage());
    }
}
}
