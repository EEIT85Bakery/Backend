package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@DynamicUpdate
@Entity
@Table(name = "wish_list_items")
public class WishListItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @ManyToOne
    @JoinColumn(name = "products_id", nullable = false)
    @JsonBackReference("Products_WishListItems")
    private Products products; // 與 Product 資料表關聯

    @ManyToOne
    @JoinColumn(name = "wish_list_id", nullable = false)
    @JsonBackReference("WishList_WishListItems")
    private WishList wishList; // 關聯到 WishList


    public WishListItems(){};

    public WishListItems(LocalDateTime createTime, LocalDateTime updateTime, Products products, WishList wishList) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.products = products;
        this.wishList = wishList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }
}


