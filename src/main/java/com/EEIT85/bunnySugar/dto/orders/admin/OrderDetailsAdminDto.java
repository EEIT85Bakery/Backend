package com.EEIT85.bunnySugar.dto.orders.admin;

public class OrderDetailsAdminDto {
    private String productName; // 產品名稱
    private Integer quantity; //數量
    private Integer price; //單價

    public OrderDetailsAdminDto(String productName, Integer quantity, Integer price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
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

}
