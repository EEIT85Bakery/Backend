package com.EEIT85.bunnySugar.dto.orders.Admin;

import java.time.LocalDateTime;

public class OrderDetailsAdminDto {
    private String productName; // 產品名稱
    private Integer quantity; //數量
    private Integer price; //單價
    private LocalDateTime pickupTime;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }
}
