package com.EEIT85.bunnySugar.dto.orders.front;

import java.time.LocalDateTime;

public class OrdersInfoDto {
    private String orderNumber;
    private LocalDateTime createTime; // 訂單創建時間
    private Integer paidPrice; // 已付款金額
    private String pickupStatus; // 取貨狀態

    // 構造函數
    public OrdersInfoDto(String orderNumber, LocalDateTime createTime, Integer paidPrice, String pickupStatus) {
        this.orderNumber = orderNumber;
        this.createTime = createTime;
        this.paidPrice = paidPrice;
        this.pickupStatus = pickupStatus;
    }

    // Getters and setters
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }
}



