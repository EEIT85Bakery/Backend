package com.EEIT85.bunnySugar.service;

import com.EEIT85.bunnySugar.dto.ChatMessageDto; // 引入 DTO
import com.EEIT85.bunnySugar.entity.ChatMessage;
import com.EEIT85.bunnySugar.repository.ChatMessageRepository; // 引入 Repository
import com.EEIT85.bunnySugar.utils.JwtUtil; // 引入 JWT 工具
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChatService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final SimpMessagingTemplate messagingTemplate;

    public ChatService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 接收用戶或客服發送的訊息
    public void sendMessage(ChatMessageDto chatMessageDto) {
        // 解析 JWT，提取發送者的帳號
        Map<String, Object> claims = jwtUtil.parseJwtToken(chatMessageDto.getJwt());
        String senderId = claims.get("account").toString();
        logger.info("Received message: '" + chatMessageDto.getContent() + "' from sender: " + senderId + " to recipient: " + chatMessageDto.getRecipientId());

        // 將 DTO 轉換為 Entity
        ChatMessage messageEntity = new ChatMessage();
        messageEntity.setContent(chatMessageDto.getContent());
        messageEntity.setSenderId(senderId);
        messageEntity.setRecipientId(chatMessageDto.getRecipientId());
        messageEntity.setTimestamp(LocalDateTime.now());

        // 儲存訊息到資料庫
        chatMessageRepository.save(messageEntity);
        logger.info("Message saved to database with content: '" + messageEntity.getContent() + "' from sender: " + messageEntity.getSenderId() + " to recipient: " + messageEntity.getRecipientId());

        // 創建發送的訊息 payload
        Map<String, Object> payload = createMessagePayload(messageEntity);
        logger.info("Sending payload: " + payload); // 印出將要發送的 payload

        // 發送訊息給目標用戶 (用戶發送給管理員，管理員發送給用戶)
        if ("ADMIN".equals(chatMessageDto.getRecipientId())) {
            // 當用戶發送訊息給管理員
            messagingTemplate.convertAndSendToUser("ADMIN", "/topic/messages", payload);
            logger.info("Message sent to ADMIN's /topic/messages");
        } else {
            // 當管理員回覆用戶
            messagingTemplate.convertAndSendToUser(chatMessageDto.getRecipientId(), "/topic/messages", payload);
            logger.info("Message sent to user " + chatMessageDto.getRecipientId() + "'s /topic/messages");
        }
    }

    // 獲取用戶與管理員之間的聊天歷史
    public List<ChatMessage> getChatMessages(String userId) {
        List<ChatMessage> messages = new ArrayList<>();

        // 獲取用戶發送給管理員的消息
        messages.addAll(chatMessageRepository.findBySenderIdAndRecipientId(userId, "ADMIN"));

        // 獲取管理員發送給用戶的消息
        messages.addAll(chatMessageRepository.findBySenderIdAndRecipientId("ADMIN", userId));

        // 根據時間戳排序
        Collections.sort(messages, Comparator.comparing(ChatMessage::getTimestamp));

        return messages;
    }

    // 創建訊息的 Payload，以便發送時包含 senderId 和 message
    private Map<String, Object> createMessagePayload(ChatMessage messageEntity) {
        return Map.of(
                "senderId", messageEntity.getSenderId(),
                "recipientId", messageEntity.getRecipientId(),
                "content", messageEntity.getContent(),
                "timestamp", messageEntity.getTimestamp()
        );
    }
}
