package com.queue.creator.service;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomMessageListenerTest {

    @InjectMocks
    private CustomMessageListener customListener;

    @BeforeEach
    public void setUp(){
      MockitoAnnotations.openMocks(this);
     }

    @Test
    public void testOnMessage_Success() throws JMSException {
        TextMessage textMessage = mock(TextMessage.class);
        when(textMessage.getText()).thenReturn("Testing Message Queue!!!");
        customListener.onMessage(textMessage);
        verify(textMessage).getText();
        assertEquals("Testing Message Queue!!!", textMessage.getText());
    }

    @Test
    public void testOnMessage_InvalidMessageType() throws Exception {
        Message message = mock(Message.class);
        customListener.onMessage(message);
    }

    @Test
    public void testOnMessage_JMSException() throws Exception {
        TextMessage textMessage = mock(TextMessage.class);
        when(textMessage.getText()).thenThrow(new JMSException("JMS Test exception"));
        customListener.onMessage(textMessage);
        verify(textMessage).getText();
    }

    @Test
    public void testOnMessage_EmptyTextMessage() throws Exception {
        TextMessage textMessage = mock(TextMessage.class);
        when(textMessage.getText()).thenReturn("");

        customListener.onMessage(textMessage);
        verify(textMessage).getText();
    }

    @Test
    public void testOnMessage_ExceptionInProcessing() throws Exception {
        TextMessage textMessage = mock(TextMessage.class);
        when(textMessage.getText()).thenThrow(new RuntimeException("Test exception"));

        assertDoesNotThrow(() -> customListener.onMessage(textMessage));
    }
}
