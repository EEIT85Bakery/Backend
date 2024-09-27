package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT new com.EEIT85.bunnySugar.dto.OrdersAdminSelectDto(o.id, o.orderNumber, " +
            "o.total, o.couponName, o.paymentPrice, o.pickupStatus) " +
            "FROM Orders o")
    Page<OrdersFullInfoAdminDto> findAllOrdersAdminSelectDto(Pageable pageable);

}
