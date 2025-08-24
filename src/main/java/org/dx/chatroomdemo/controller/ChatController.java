package org.dx.chatroomdemo.controller;

import org.dx.chatroomdemo.Entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessagingTemplate template;

    /*
    一对一发送消息
     */
    @MessageMapping("/chat.sendToOne")
    public void sendToOne(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String userId = (String) headerAccessor.getSessionAttributes().put("userId", chatMessage.getSender());
        template.convertAndSend("/topic/user" + chatMessage.getReceiver(), chatMessage);
        logger.info("从 " + userId + " 向 " + chatMessage.getReceiver() + " 发送消息: " + chatMessage.getContent());
    }

    /*
    一对多发送消息（聊天室）
     */
    @MessageMapping("/chat.sendToAll")
    @SendTo("/queue/chat")
    public ChatMessage sendToAll(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        return chatMessage;
    }

    /*
    一对多发送消息（聊天室）,使用template
     */
    @MessageMapping("/chat.sendToAll2")
    public void sendToAllByTemplate(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        template.convertAndSend("/queue/chat", chatMessage);
    }
}
