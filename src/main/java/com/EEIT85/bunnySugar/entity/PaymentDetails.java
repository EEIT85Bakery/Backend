package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "payment_details")
@Entity
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "orders_id", nullable = false)
    @JsonBackReference("Orders_PaymentDetails")
    private Orders orders;

    @Column(name = "merchant_no", nullable = false)
    private String merchantNo;

    @Column(name =  "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "paid_price", nullable = false)
    private Integer paidPrice;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    public PaymentDetails() {
    }

    public PaymentDetails(Orders orders, String merchantNo, String paymentMethod, String paymentStatus, Integer paidPrice, LocalDateTime paymentDate, LocalDateTime createTime, LocalDateTime updateTime) {
        this.orders = orders;
        this.merchantNo = merchantNo;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paidPrice = paidPrice;
        this.paymentDate = paymentDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
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

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "id=" + id +
                ", merchantNo='" + merchantNo + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paidPrice=" + paidPrice +
                ", paymentDate=" + paymentDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
