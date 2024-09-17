package com.EEIT85.bunnySugar.dto.cart;

import java.time.LocalDateTime;


public class CartInsertDto {
    private Long productId;
    private Integer quantity;    // 數量
    private Integer price;

    public CartInsertDto() {
    }

    public CartInsertDto(Long productId) {
        this.productId = productId;
    }

    public CartInsertDto(Long productId, Integer quantity, Integer price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
}
