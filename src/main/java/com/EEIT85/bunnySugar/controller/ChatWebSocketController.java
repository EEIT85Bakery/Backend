package com.EEIT85.bunnySugar.controller;

import com.EEIT85.bunnySugar.dto.ChatMessageDto;
import com.EEIT85.bunnySugar.entity.ChatMessage;
import com.EEIT85.bunnySugar.service.ChatService; // 引用 Service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class ChatWebSocketController {

    @Autowired
    private ChatService chatService; // 注入 Service

    @MessageMapping("/send") // 當接收到 /app/send 的訊息時
    public void sendMessage(@Payload ChatMessageDto chatMessage) {
        // 設置當前時間戳
        chatMessage.setTimestamp(LocalDateTime.now());
        chatService.sendMessage(chatMessage); // 將訊息發送到 Service 處理
    }

    @GetMapping("/get/messages") // 新增獲取聊天歷史的 API
    public List<ChatMessage> getChatMessages(@RequestParam String userId) {
        return chatService.getChatMessages(userId); // 調用 Service 獲取消息
    }
}
