package com.EEIT85.bunnySugar.dto.cart;

public class CartItemDto {
    private Long id;
    private Integer price;
    private Integer quantity;
    private String productName;
    private String imageUrl;
    private Integer stocks;

    public CartItemDto() {
    }

    public CartItemDto(Long id, Integer price, Integer quantity,
                       String productName, String imageUrl, Integer stocks) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.stocks = stocks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    // getters and setters
}