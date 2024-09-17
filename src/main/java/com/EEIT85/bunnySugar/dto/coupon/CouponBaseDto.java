package com.EEIT85.bunnySugar.dto.coupon;

import java.time.LocalDate;

public class CouponBaseDto {
    private String couponName;
    private Integer discountNumber;
    private LocalDate endDate;
    private Boolean enable;
    private Integer leastPriceForDiscount;

    public CouponBaseDto() {
    }

    public CouponBaseDto(String couponName, Integer discountNumber, LocalDate endDate, Boolean enable, Integer leastPriceForDiscount) {
        this.couponName = couponName;
        this.discountNumber = discountNumber;
        this.endDate = endDate;
        this.enable = enable;
        this.leastPriceForDiscount = leastPriceForDiscount;
    }

    public Integer getLeastPriceForDiscount() {
        return leastPriceForDiscount;
    }

    public void setLeastPriceForDiscount(Integer leastPriceForDiscount) {
        this.leastPriceForDiscount = leastPriceForDiscount;
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
}
