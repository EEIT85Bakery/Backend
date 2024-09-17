package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@DynamicUpdate
@Entity
@Table(name = "cart_items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference("Cart_CartItems")
    private Cart cart;

    @JsonBackReference("Products_CartItems")
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products products;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "price", nullable = false)
    private Integer price;

    public CartItems() {
    }

//    public CartItems(Long id, Products products, Cart cart,
//                      Integer price, Integer quantity, LocalDateTime updateTime,
//                     LocalDateTime createTime) {
//        this.price = price;
//        this.updateTime = updateTime;
//        this.createTime = createTime;
//        this.quantity = quantity;
//        this.products = products;
//        this.cart = cart;
//        this.id = id;
//    }




    public CartItems(Cart cart, Products products, Integer quantity, Integer price
            , LocalDateTime createTime, LocalDateTime updateTime) {
        this.cart = cart;
        this.products = products;
        this.quantity = quantity;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}