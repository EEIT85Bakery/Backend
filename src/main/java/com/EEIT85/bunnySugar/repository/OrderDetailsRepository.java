package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsFrontDto;
import com.EEIT85.bunnySugar.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
//    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsFrontDto(od.products.productName, od.quantity, od.price) "
//            + "FROM OrderDetails od "
//            + "JOIN od.orders o "
//            + "WHERE o.orderNumber = :orderNumber")
//    List<OrderDetailsFrontDto> findOrderDetailsByOrderNumber(@Param("orderNumber") String orderNumber);
}


