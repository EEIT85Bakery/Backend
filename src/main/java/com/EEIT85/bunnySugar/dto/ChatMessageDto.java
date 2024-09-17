package com.EEIT85.bunnySugar.dto;

import java.time.LocalDateTime;

public class ChatMessageDto {
    private String content;
    private String recipientId;
    private String senderId; // 新增發件人ID
    private String jwt;
    private LocalDateTime timestamp; // 新增時間戳

    // 無參數構造函數
    public ChatMessageDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getSenderId() {
        return senderId; // 新增取得發件人ID的方法
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId; // 新增設置發件人ID的方法
    }

    public LocalDateTime getTimestamp() {
        return timestamp; // 新增取得時間戳的方法
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp; // 新增設置時間戳的方法
    }

    public String getJwt() { return jwt; }

    public void setJwt(String jwt) { this.jwt = jwt; }
}
