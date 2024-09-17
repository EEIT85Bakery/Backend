package com.EEIT85.bunnySugar.dto.orders.front;

import java.time.LocalDateTime;

public class OrderDetailsFrontDto {
    private String productName;
    private Integer quantity; // 數量
    private Integer price; //單價
    private String img1;
    private Integer subtotal;


    public OrderDetailsFrontDto() {
    }



    public OrderDetailsFrontDto(String productName, Integer quantity, Integer price, String img1, Integer subtotal) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.img1 = img1;
        this.subtotal = subtotal;
    }

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

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}


