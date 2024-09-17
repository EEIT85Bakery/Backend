package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("WishList_WishListItems")
    private Set<WishListItems> wishListItems = new HashSet<>();

    @Column(nullable = false, name = "create_time")
    private LocalDateTime createTime;

    @Column(nullable = false, name = "update_time")
    private LocalDateTime updateTime;

    public WishList() {
    }

    public Set<WishListItems> getWishListItems() {
        return wishListItems;
    }

    public WishList(Users users, Set<WishListItems> wishListItems, LocalDateTime createTime, LocalDateTime updateTime) {
        this.users = users;
        this.wishListItems = wishListItems;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public void setWishListItems(Set<WishListItems> wishListItems) {
        this.wishListItems = wishListItems;
    }

    public WishList(Users users, Products products, LocalDateTime createTime, LocalDateTime updateTime) {
        this.users = users;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WishList(Long id, Users users, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.users = users;
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