package com.queue.creator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.queue.creator.model.EmployeeBean;
import com.queue.creator.model.MessageBean;
import com.queue.creator.service.MessageSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    public void testSendMessage() throws Exception {
        MessageBean messageBean = new MessageBean();
        messageBean.setQueueName("testQueue");
        messageBean.setMessage("testMessage");

        ObjectMapper objectMapper = new ObjectMapper();
        String messageBeanJson = objectMapper.writeValueAsString(messageBean);
        String expectedResponse = "Sending message to Queue Name: " + messageBean.getQueueName() + " with Message: " + messageBean.getMessage();

        mockMvc.perform(post("/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(messageBeanJson))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
        verify(messageSender).send(messageBean.getQueueName(), messageBean.getMessage());
    }

    @Test
    public void testRegistration() throws Exception {
        String queueName = "testQueue";
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmpId(1);
        employeeBean.setEmpName("Test");
        employeeBean.setEmpAddress("LocalTest");
        employeeBean.setEmpDoj("10-10-0000");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employeeBean);
        String expectedResponse = "Sending message to Queue Name: " + queueName + " with Message: " + employeeBean;

        mockMvc.perform(post("/registration/{queueName}", queueName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

          verify(messageSender).send(queueName, employeeBean);
    }

}
