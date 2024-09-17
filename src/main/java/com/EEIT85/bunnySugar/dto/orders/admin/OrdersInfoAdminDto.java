package com.EEIT85.bunnySugar.dto.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsFrontDto;

import java.util.List;

public class OrdersInfoAdminDto { //訂單管理頁面(不含跳出的詳細資訊)
    private Long orderId;
    private String orderNumber;
    private String userName;
    private String userPhone;
    private List<OrderDetailsFrontDto> orderDetails;
    private Integer paidPrice;
    private String paymentStatus;
    private String pickupStatus;

    public OrdersInfoAdminDto() {
    }

    public OrdersInfoAdminDto(Long orderId, String orderNumber, String userName, String userPhone, Integer paidPrice, String paymentStatus, String pickupStatus) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.userName = userName;
        this.userPhone = userPhone;
        this.paidPrice = paidPrice;
        this.paymentStatus = paymentStatus;
        this.pickupStatus = pickupStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public List<OrderDetailsFrontDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsFrontDto> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
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
}



