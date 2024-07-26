package com.queue.creator.service;

import com.queue.creator.model.EmployeeBean;
import com.queue.creator.xmlroot.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testOnMessage_ObjectMessage_Student() throws JMSException {
        ObjectMessage mockObjectMessage = mock(ObjectMessage.class);
        Student std = new Student();
        std.setStdId(1);
        std.setStdName("Test");
        std.setStdAddr("Local");

        when(mockObjectMessage.getObject()).thenReturn(std);
        customListener.onMessage(mockObjectMessage);
        verify(mockObjectMessage).getObject();
        assertEquals(std, mockObjectMessage.getObject());
    }

    @Test
    public void testOnMessage_ObjectMessage_Employee() throws JMSException {
        ObjectMessage mockObjectMessage = mock(ObjectMessage.class);
        EmployeeBean emp = new EmployeeBean();
        emp.setEmpId(1);
        emp.setEmpName("Test");
        emp.setEmpAddress("Local");
        emp.setEmpDoj("10-10-1000");

        when(mockObjectMessage.getObject()).thenReturn(emp);
        customListener.onMessage(mockObjectMessage);
        verify(mockObjectMessage).getObject();
        assertEquals(emp, mockObjectMessage.getObject());
    }

    @Test
    public void testOnMessage_UnsupportedMessageType() throws Exception {
        Message message = mock(Message.class);
        customListener.onMessage(message);
    }

    @Test
    public void testOnMessage_JMSException() throws Exception {
        TextMessage textMessage = mock(TextMessage.class);
        when(textMessage.getText()).thenThrow(new JMSException("Testing JMSException"));
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
    public void testOnMessage_RuntimeException() throws Exception {
        TextMessage textMessage = mock(TextMessage.class);
        when(textMessage.getText()).thenThrow(new RuntimeException("Testing Runtime Exception"));
        customListener.onMessage(textMessage);
        verify(textMessage).getText();
    }
}
