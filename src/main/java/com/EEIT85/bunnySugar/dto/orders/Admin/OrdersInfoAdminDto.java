package com.EEIT85.bunnySugar.dto.orders.Admin;

import java.util.List;

public class OrdersInfoAdminDto { //訂單管理頁面(不含跳出的詳細資訊)
    private String orderNumber;
    private String userName;
    private String userPhone;
    private String userEmail;
    private List<OrderDetailsAdminDto> orderDetails;
    private Integer paymentPrice;
    private String paymentStatus;

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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<OrderDetailsAdminDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsAdminDto> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(Integer paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}



