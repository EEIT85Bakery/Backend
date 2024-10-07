package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@DynamicUpdate
@Entity
@Table(name = "wish_list")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonBackReference("Users_WishList")
    @JoinColumn(name = "users_id", nullable = false)
    private Users users;

    @OneToOne
    @JsonBackReference("Products_WishList")
    @JoinColumn(name = "products_id", nullable = false)
    private  Products products;

    @Column(nullable = false, name = "create_time")
    private LocalDateTime createTime;

    @Column(nullable = false, name = "update_time")
    private LocalDateTime updateTime;

    public WishList() {
    }

    public WishList(Users users, Products products, LocalDateTime createTime, LocalDateTime updateTime) {
        this.users = users;
        this.products = products;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WishList(Long id, Users users, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.users = users;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
}