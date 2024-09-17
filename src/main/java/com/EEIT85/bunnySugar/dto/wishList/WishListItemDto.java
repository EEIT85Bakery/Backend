package com.EEIT85.bunnySugar.dto.wishList;

public class WishListItemDto {

    private Long productId;
    private String img1;
    private String productName;
    private Integer price;

    public WishListItemDto(Long productId, String img1, String productName, Integer price) {
        this.productId = productId;
        this.img1 = img1;
        this.productName = productName;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
