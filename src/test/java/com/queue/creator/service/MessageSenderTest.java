package com.queue.creator.service;

import com.queue.creator.model.EmployeeBean;
import com.queue.creator.model.MessageBean;
import com.queue.creator.xmlroot.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;

public class MessageSenderTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    private Session session;

    @Mock
    private TextMessage textMessage;

    @Mock
    private ObjectMessage objectMessage;

    @InjectMocks
    private MessageSender messageSender;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendStringMessage() throws JMSException {
        when(session.createTextMessage(any(String.class))).thenReturn(textMessage);
        MessageBean messageBean = new MessageBean();
        messageBean.setQueueName("testQueue");
        messageBean.setMessage("Test Message");
        messageSender.send(messageBean.getQueueName(), messageBean.getMessage());

        //ArgumentCaptor is used to capture the MessageCreator passed to the jmsTemplate.send method
        ArgumentCaptor<MessageCreator> messageCreatorCaptor = ArgumentCaptor.forClass(MessageCreator.class);

        //After capturing the MessageCreator, we manually call its createMessage method with the mocked Session
        // to ensure the correct interactions are verified.
        verify(jmsTemplate).send(eq(messageBean.getQueueName()), messageCreatorCaptor.capture());
        messageCreatorCaptor.getValue().createMessage(session);

        verify(session).createTextMessage(messageBean.getMessage());
    }

    @Test
    public void testSendStudentMessage() throws JMSException {
        Student student = new Student();
        when(session.createObjectMessage(any(Student.class))).thenReturn(objectMessage);

        messageSender.send("testQueue", student);

        ArgumentCaptor<MessageCreator> messageCreatorCaptor = ArgumentCaptor.forClass(MessageCreator.class);
        verify(jmsTemplate).send(eq("testQueue"), messageCreatorCaptor.capture());
        messageCreatorCaptor.getValue().createMessage(session);

        verify(session).createObjectMessage(student);
    }

    @Test
    public void testSendEmployeeMessage() throws JMSException {
        EmployeeBean employee = new EmployeeBean();
        when(session.createObjectMessage(any(EmployeeBean.class))).thenReturn(objectMessage);

        messageSender.send("testQueue", employee);

        ArgumentCaptor<MessageCreator> messageCreatorCaptor = ArgumentCaptor.forClass(MessageCreator.class);
        verify(jmsTemplate).send(eq("testQueue"), messageCreatorCaptor.capture());
        messageCreatorCaptor.getValue().createMessage(session);

        verify(session).createObjectMessage(employee);
    }

    @Test
    public void testSendUnsupportedMessage() {
        Object unsupportedMessage = new Object();

        assertThrows(IllegalArgumentException.class, () -> messageSender.send("testQueue", unsupportedMessage));
    }
}