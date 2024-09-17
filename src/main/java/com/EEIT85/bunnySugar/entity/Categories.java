package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;

@DynamicUpdate
@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "categories")
    //管理的一方(避免無窮層遞)
    @JsonManagedReference("Products_Categories")
    private List<Products> products;

    @Column(length = 25, nullable = false)
    private String categoryName;
    @Column(length = 25, nullable = false)
    private String flavor;
    @Column(length = 255, nullable = false)
    private String categoryDescription;
    @Column(nullable = false)
    private LocalDateTime createTime;
    @Column(nullable = false)
    private LocalDateTime updateTime;

    public Categories() {
    }

    public Categories(String categoryName, String flavor,
                      String categoryDescription, LocalDateTime createTime, LocalDateTime updateTime) {
        this.categoryName = categoryName;
        this.flavor = flavor;
        this.categoryDescription = categoryDescription;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
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