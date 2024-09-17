package com.EEIT85.bunnySugar.dto.orders.front;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersFrontDto {
    private List<OrderDetailsFrontDto> orderDetails;
    private Integer total; // 尚未折扣的總金額
    private String couponName; // 使用的折扣碼
    private Integer usedBunnyCoins; // 使用多少bunny coin
    private Integer paidPrice; // 付款金額
    private String userName;
    private String userPhone;
    private String userEmail;
    private String orderNumber;
    private LocalDateTime createTime;
    private String paymentMethod;
    private String paymentStatus;
    private String pickupStatus;
    private LocalDateTime pickupTime;

    public OrdersFrontDto() {
    }

    public OrdersFrontDto(List<OrderDetailsFrontDto> orderDetails, Integer total, String couponName, Integer usedBunnyCoins, Integer paidPrice, String userName, String userPhone, String userEmail, String orderNumber, LocalDateTime createTime, String paymentMethod, String paymentStatus, String pickupStatus, LocalDateTime pickupTime) {
        this.orderDetails = orderDetails;
        this.total = total;
        this.couponName = couponName;
        this.usedBunnyCoins = usedBunnyCoins;
        this.paidPrice = paidPrice;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.orderNumber = orderNumber;
        this.createTime = createTime;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.pickupStatus = pickupStatus;
        this.pickupTime = pickupTime;
    }

    public OrdersFrontDto(String orderNumber, LocalDateTime createTime, String paymentMethod, String paymentStatus, String pickupStatus,
                          LocalDateTime pickupTime, Integer total, String couponName, Integer usedBunnyCoins,
                          Integer paidPrice, String userName, String userPhone, String userEmail) {
        this.orderNumber = orderNumber;
        this.createTime = createTime;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.pickupStatus = pickupStatus;
        this.pickupTime = pickupTime;
        this.total = total;
        this.couponName = couponName;
        this.usedBunnyCoins = usedBunnyCoins;
        this.paidPrice = paidPrice;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public List<OrderDetailsFrontDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsFrontDto> orderDetails) {
        this.orderDetails = orderDetails;
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

    public Integer getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
