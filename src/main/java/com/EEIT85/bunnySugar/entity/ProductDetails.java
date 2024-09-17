package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@DynamicUpdate
@Entity
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 主鍵ID


    @OneToOne
    //被管理的一方(避免無窮層遞)
    @JsonBackReference("Products_ProductDetails")
    @JoinColumn(name = "products_id", nullable = false)
    private Products products;  // 對應 products 表的 ID
    @Column(length = 255, nullable = false)
    private String description;  // 商品描述
    @Column(nullable = false)
    private Integer price;  // 價格
    @Column(length = 1000, nullable = false)
    private String materialDescription;  // 組成成份
    @Column(nullable = false)
    private LocalDateTime createTime;  // 創建時間
    @Column(nullable = false)
    private LocalDateTime updateTime;  // 修改時間
    @Column(name = "enable", nullable = false)
    private Boolean enable;  // 是否啟用

    @Column(name = "img1")
    private String img1;

    @Column(name = "img2")
    private String img2;

    @Column(name = "img3")
    private String img3;

    @Column(name = "img4")
    private String img4;


    public ProductDetails() {
    }

    public ProductDetails(Products products, String description, Integer price, String materialDescription, LocalDateTime createTime, LocalDateTime updateTime, Boolean enable, String img1, String img2, String img3, String img4) {
        this.products = products;
        this.description = description;
        this.price = price;
        this.materialDescription = materialDescription;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.enable = enable;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
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

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }



}