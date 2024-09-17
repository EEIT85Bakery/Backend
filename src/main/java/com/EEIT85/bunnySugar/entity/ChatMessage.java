package com.EEIT85.bunnySugar.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String senderId; // 用來存放發送者ID

    @Column(nullable = false)
    private String recipientId; // 用來存放接收者ID

    @Column(nullable = false)
    private LocalDateTime timestamp; // 使用 LocalDateTime 來存放發送時間

    // Constructors
    public ChatMessage() {}

    public ChatMessage(String content, String senderId, String recipientId, LocalDateTime timestamp) {
        this.content = content;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
