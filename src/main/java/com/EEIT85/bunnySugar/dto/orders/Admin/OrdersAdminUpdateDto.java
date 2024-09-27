package com.EEIT85.bunnySugar.dto.orders.Admin;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersAdminUpdateDto {
    private Long id;
    private String pickupStatus;
    private List<OrderDetailsAdminUpdateDto> orderDetails;

    public OrdersAdminUpdateDto() {
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderDetailsAdminUpdateDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsAdminUpdateDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
