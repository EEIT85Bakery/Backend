package com.EEIT85.bunnySugar.dto.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsFrontDto;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersFullInfoAdminDto {
    //訂單管理頁面所跳出的詳細資訊
    private Long orderId;
    private String orderNumber;
    private String userName;
    private String userPhone;
    private String userEmail;
    private List<OrderDetailsFrontDto> orderDetails;
    private LocalDateTime createTime;
    private String paymentStatus;
    private String pickupStatus;
    private Integer paidPrice;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private Integer total;
    private String couponName;
    private Integer usedBunnyCoins;
    private LocalDateTime pickupTime;
    private Integer gameTimes;

    public OrdersFullInfoAdminDto(Long orderId, String orderNumber, String userName, String userPhone, String userEmail, LocalDateTime createTime, String paymentStatus, String pickupStatus, Integer paidPrice, String paymentMethod, LocalDateTime paymentDate, Integer total, String couponName, Integer usedBunnyCoins, LocalDateTime pickupTime, Integer gameTimes) {
        this.orderId = orderId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.orderNumber = orderNumber;
        this.createTime = createTime;
        this.paymentStatus = paymentStatus;
        this.pickupStatus = pickupStatus;
        this.paidPrice = paidPrice;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.total = total;
        this.couponName = couponName;
        this.usedBunnyCoins = usedBunnyCoins;
        this.pickupTime = pickupTime;
        this.gameTimes = gameTimes;

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public List<OrderDetailsFrontDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsFrontDto> orderDetails) {
        this.orderDetails = orderDetails;
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

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    public Integer getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
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

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }


    public Integer getGameTimes() {
        return gameTimes;
    }

    public void setGameTimes(Integer gameTimes) {
        this.gameTimes = gameTimes;
    }
}



