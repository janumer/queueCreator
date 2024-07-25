package com.queue.creator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        mockMvc.perform(post("/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(messageBeanJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Message sent to queue: testQueue"));
        verify(messageSender).send("testQueue", "testMessage");
    }
}
