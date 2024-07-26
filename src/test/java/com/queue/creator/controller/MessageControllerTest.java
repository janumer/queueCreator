package com.queue.creator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.queue.creator.model.EmployeeBean;
import com.queue.creator.model.MessageBean;
import com.queue.creator.service.MessageSender;
import com.queue.creator.xmlroot.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageSender messageSender;

    @InjectMocks
    private MessageController messageController;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    @Test
    public void testSendMessage() throws Exception {
        MessageBean messageBean = new MessageBean();
        messageBean.setQueueName("testQueue");
        messageBean.setMessage("Test Message");

        mockMvc.perform(post("/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messageBean)))
                .andExpect(status().isOk())
                .andExpect(content().string("Sending message to Queue Name: testQueue with Message: Test Message"));

        verify(messageSender).send(messageBean.getQueueName(), messageBean.getMessage());
    }

    @Test
    public void testSendEmployee() throws Exception {
        String queueName = "testQueue1";
        EmployeeBean emp = new EmployeeBean();
        emp.setEmpId(1);
        emp.setEmpName("Test");
        emp.setEmpAddress("LocalTest");
        emp.setEmpDoj("10-10-0000");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(emp);
        String expectedResponse = "Sending message to Queue Name: " + queueName + " with Message: " + emp;

        mockMvc.perform(post("/employee/{queueName}", queueName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

          verify(messageSender).send(queueName, emp);
    }

    @Test
    public void testSendStudent() throws Exception {
        String queueName = "testQueue";

        Student std = new Student();
        std.setStdId(1);
        std.setStdName("Test");
        std.setStdAddr("Local");

        XmlMapper xmlMapper = new XmlMapper();
        String requestBody = xmlMapper.writeValueAsString(std);
        String expectedResponse = "Sending message to Queue Name: "+ queueName +" with Message: " +std;
        mockMvc.perform(post("/student/{queueName}", queueName)
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

        verify(messageSender).send(queueName, std);
    }

}
