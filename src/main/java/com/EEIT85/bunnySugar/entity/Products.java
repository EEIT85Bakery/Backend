package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

//@Entity標記此物件對應資料庫欄位
@Entity
//對應資料庫表名稱(會自動幫你產生)
@Table(name = "products")
public class Products {

    //主鍵
    @Id
    //mysql自增長id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    //長度、不允許null、資料庫欄位名稱(物件屬性與資料庫欄位一樣可不寫)
    @Column(name = "name", length = 55, nullable = false)
    private String name;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "stocks", nullable = false)
    private Integer stocks;

    //ManyToOne預設為EAGER如果每次呼叫商品都會需要種類就使用預設值EAGER 否則LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    //產生欄位categories_id自動對應Categories的id
    @JoinColumn(name = "categories_id", nullable = false)
    //被管理的一方(避免無窮層遞)
    @JsonBackReference
    private Categories categories;

    //管理的一方(避免無窮層遞)
    @JsonManagedReference
    //因為products改變後details有要跟著變 所以cascadeAll
    //orphanRemoval 如果products被刪除details沒有對應關聯 就會自動被刪除
    @OneToOne(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval =
            true)
    private ProductDetails productDetails;  // 新增 ProductDetails 映射

    public Products() {
    }

    public Products(String name, LocalDateTime createTime, LocalDateTime updateTime, Integer stocks) {
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.stocks = stocks;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", stocks=" + stocks +
                ", categories=" + categories +
                ", productDetails=" + productDetails +
                '}';
    }
}