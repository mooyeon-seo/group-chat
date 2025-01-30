package com.insufficient.chat.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.insufficient.chat.dto.MessageDTO;

@Controller
public class ChatController {

    @MessageMapping("/send/{groupId}") // Clients send messages here
    @SendTo("/topic/group/{groupId}") // Broadcast to all users in the group
    public MessageDTO sendMessage(@Payload MessageDTO message) {
        System.out.println("Sending message: " + message);

        return new MessageDTO(
                UUID.randomUUID().toString(),
                message.content(),
                message.senderId(),
                message.groupId(),
                LocalDateTime.now()); // Message is sent to all users in the group
    }
}
