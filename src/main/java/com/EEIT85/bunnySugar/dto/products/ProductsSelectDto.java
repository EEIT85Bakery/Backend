package com.EEIT85.bunnySugar.dto.products;

import jakarta.persistence.Column;

public class ProductsSelectDto {
    private Long id;
    private String productName;
    private Integer stocks;
    private String description;
    private Integer price;
    private String imageUrl;
    private String materialDescription;
    private String categoryName;
    private String flavor;
    private String categoryDescription;
    private String img1;

    private String img2;

    private String img3;

    private String img4;

    public ProductsSelectDto() {
    }

    public ProductsSelectDto(Long id, String productName, Integer stocks, String description, Integer price, String imageUrl, String materialDescription, String categoryName, String flavor, String categoryDescription, String img1, String img2, String img3, String img4) {
        this.id = id;
        this.productName = productName;
        this.stocks = stocks;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.materialDescription = materialDescription;
        this.categoryName = categoryName;
        this.flavor = flavor;
        this.categoryDescription = categoryDescription;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public ProductsSelectDto(Long id, String productName, Integer price) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }


}