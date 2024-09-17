package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;

@DynamicUpdate
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "bigint")
    private Long id;

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonManagedReference("Orders_PaymentDetails")
    private PaymentDetails paymentDetails;

    @JsonBackReference("Users_Orders")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    //orphanRemoval=true，如果orders被刪除details沒有對應關聯就會自動被刪除
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("orders_orderDetails")
    private List<OrderDetails> orderDetails;


    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "total", nullable = false)
    private Integer total;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "used_bunny_coins")
    private Integer usedBunnyCoins;

    @Column(name = "payment_price")
    private Integer paymentPrice;

    @Column(name = "pickup_status", nullable = false)
    private String pickupStatus;

    @Column(name = "pickup_time")
    private LocalDateTime pickupTime;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "uptade_time", nullable = false)
    private LocalDateTime updateTime;

    public Orders() {
    }

    public Orders(PaymentDetails paymentDetails, String orderNumber, Integer total, String couponName, Integer usedBunnyCoins, Integer paymentPrice, String pickupStatus, LocalDateTime pickupTime, LocalDateTime createTime, LocalDateTime updateTime, Users user, List<OrderDetails> orderDetails) {
        this.paymentDetails = paymentDetails;
        this.orderNumber = orderNumber;
        this.total = total;
        this.couponName = couponName;
        this.usedBunnyCoins = usedBunnyCoins;
        this.paymentPrice = paymentPrice;
        this.pickupStatus = pickupStatus;
        this.pickupTime = pickupTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
        this.orderDetails = orderDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getUsedBunnyCoins() {
        return usedBunnyCoins;
    }

    public void setUsedBunnyCoins(Integer usedBunnyCoins) {
        this.usedBunnyCoins = usedBunnyCoins;
    }

    public Integer getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(Integer paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", paymentDetails=" + paymentDetails +
                ", orderNumber='" + orderNumber + '\'' +
                ", total=" + total +
                ", couponName='" + couponName + '\'' +
                ", usedBunnyCoins=" + usedBunnyCoins +
                ", paymentPrice=" + paymentPrice +
                ", pickupStatus='" + pickupStatus + '\'' +
                ", pickupTime=" + pickupTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", user=" + user +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
