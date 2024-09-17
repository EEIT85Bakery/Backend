package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;

@DynamicUpdate
//@Entity標記此物件對應資料庫欄位
@Entity
//對應資料庫表名稱(會自動幫你產生)
@Table(name = "products")
public class Products {

    //主鍵
    @Id
    //mysql自增長id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "bigint")
    private Long id;



    //ManyToOne預設為EAGER如果每次呼叫商品都會需要種類就使用預設值EAGER 否則LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    //產生欄位categories_id自動對應Categories的id
    @JoinColumn(name = "categories_id", nullable = false)
    //被管理的一方(避免無窮層遞)
    @JsonBackReference("Products_Categories")
    private Categories categories;

    //管理的一方(避免無窮層遞)
    @JsonManagedReference("Products_ProductDetails")
    //因為products改變後details有要跟著變 所以cascadeAll
    //orphanRemoval 如果products被刪除details沒有對應關聯 就會自動被刪除
    @OneToOne(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval =
            true)
    private ProductDetails productDetails;  // 新增 ProductDetails 映射

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval =
            true)
    private List<WishListItems> wishListItems;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval =
            true)
    @JsonManagedReference("Products_CartItems")
    private List<CartItems> cartItems;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("products_orderDetails")
    private List<OrderDetails> orderDetails;

    //長度、不允許null、資料庫欄位名稱(物件屬性與資料庫欄位一樣可不寫)
    @Column(name = "product_name", length = 55, nullable = false)
    private String productName;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "stocks", nullable = false)
    private Integer stocks;

    public Products() {
    }

    public Products(Categories categories, ProductDetails productDetails, List<WishList> wishList, List<CartItems> cartItems, List<OrderDetails> orderDetails, String productName, LocalDateTime createTime, LocalDateTime updateTime, Integer stocks, List<WishListItems> wishListItems) {
        this.categories = categories;
        this.productDetails = productDetails;
        this.wishListItems = wishListItems;
        this.cartItems = cartItems;
        this.orderDetails = orderDetails;
        this.productName = productName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.stocks = stocks;
    }

    public Products(String productName, LocalDateTime createTime, LocalDateTime updateTime, Integer stocks, Categories categories, ProductDetails productDetails, List<CartItems> cartItems, List<OrderDetails> orderDetails) {
        this.productName = productName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.stocks = stocks;
        this.categories = categories;
        this.productDetails = productDetails;
        this.cartItems = cartItems;
        this.orderDetails = orderDetails;
    }

    public Products(String productName, LocalDateTime createTime, LocalDateTime updateTime, Integer stocks) {
        this.productName = productName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.stocks = stocks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WishListItems> getWishListItems() { return wishListItems; }

    public void setWishListItems(List<WishListItems> wishListItems) { this.wishListItems = wishListItems;}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + productName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", stocks=" + stocks +
                ", categories=" + categories +
                ", productDetails=" + productDetails +
                '}';
    }
}