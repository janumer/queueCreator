package com.queue.creator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Mockito.verify;

public class MessageSenderTest {

    @InjectMocks
    private MessageSender messageSender;
    @Mock
    private JmsTemplate jmsTemplate;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void sendTest_Success(){
        String queueName = "testQueue";
        String message = "testMessage";
        messageSender.send(queueName,message);
        verify(jmsTemplate).convertAndSend(queueName, message);
    }

}
