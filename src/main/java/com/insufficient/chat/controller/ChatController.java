package com.insufficient.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.insufficient.chat.model.Message;

@Controller
public class ChatController {

    @MessageMapping("/send/{groupId}")
    @SendTo("/topic/group/{groupId}")
    public Message sendMessage(@Payload Message message) {
        return message;
    }
}