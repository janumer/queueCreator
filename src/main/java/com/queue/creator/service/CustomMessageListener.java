package com.queue.creator.service;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String textMessage = ((TextMessage) message).getText();
                log.info("-->Received: {}", textMessage);
            } else {
                log.warn("Received non - text message");
            }
        } catch (JMSException e) {
            log.error("JMSException occurred while processing message: {}", e.getMessage());
        } catch (RuntimeException e) {
            log.error("RuntimeException occurred while processing message: {}", e.getMessage());
        }
    }
}
