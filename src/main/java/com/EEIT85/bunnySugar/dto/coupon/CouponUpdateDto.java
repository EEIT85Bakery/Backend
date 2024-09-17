package com.EEIT85.bunnySugar.dto.coupon;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CouponUpdateDto extends CouponBaseDto{
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public CouponUpdateDto() {
    }

    public CouponUpdateDto(String couponName, Integer discountNumber, LocalDate endDate, Boolean enable, Integer leastPriceForDiscount, LocalDateTime updateTime, LocalDateTime createTime) {
        super(couponName, discountNumber, endDate, enable, leastPriceForDiscount);
        this.updateTime = updateTime;
        this.createTime = createTime;
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
