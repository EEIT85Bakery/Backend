package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DynamicUpdate
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "users_id", nullable = false)
    @JsonBackReference("Users_Cart")
    private Users users;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Cart_CartItems")
    private Set<CartItems> cartItems = new HashSet<>();

    @Column(name = "total", nullable = false)
    private Integer total;

    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    public Cart() {
    }

    public Cart(Users users, Set<CartItems> cartItems, Integer total, Integer totalQuantity, LocalDateTime createTime, LocalDateTime updateTime) {
        this.users = users;
        this.cartItems = cartItems;
        this.total = total;
        this.totalQuantity = totalQuantity;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public Set<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", total=" + total +
                ", totalQuantity=" + totalQuantity +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}