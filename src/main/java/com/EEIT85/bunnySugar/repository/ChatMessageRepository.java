package com.EEIT85.bunnySugar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.EEIT85.bunnySugar.entity.ChatMessage;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    // 查詢用戶與管理員之間的聊天記錄
    List<ChatMessage> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
