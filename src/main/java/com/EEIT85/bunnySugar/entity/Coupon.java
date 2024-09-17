package com.EEIT85.bunnySugar.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@DynamicUpdate
@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_name" ,nullable = false)
    private String couponName;

    @Column(name = "discount_number", nullable = false)
    private Integer discountNumber;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "enable", nullable = false)
    private Boolean enable;

    @Column(nullable = false, name = "create_time")
    private LocalDateTime createTime;

    @Column(nullable = false, name = "update_time")
    private LocalDateTime updateTime;

    @Column(nullable = false, name = "least_price_for_discount")
    private Integer leastPriceForDiscount;

    public Coupon() {
    }

    public Coupon(String couponName, Integer discountNumber, LocalDate endDate, Boolean enable, LocalDateTime createTime, LocalDateTime updateTime, Integer leastPriceForDiscount) {
        this.couponName = couponName;
        this.discountNumber = discountNumber;
        this.endDate = endDate;
        this.enable = enable;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.leastPriceForDiscount = leastPriceForDiscount;
    }

    public Integer getLeastPriceForDiscount() {
        return leastPriceForDiscount;
    }

    public void setLeastPriceForDiscount(Integer leastPriceForDiscount) {
        this.leastPriceForDiscount = leastPriceForDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getDiscountNumber() {
        return discountNumber;
    }

    public void setDiscountNumber(Integer discountNumber) {
        this.discountNumber = discountNumber;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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
