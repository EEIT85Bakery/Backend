package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.orders.admin.ProductSalesDto;
import com.EEIT85.bunnySugar.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SalesReportRepository extends JpaRepository<Orders, Long> {

    // 查詢每個產品的銷售數據（包括單價、總銷售額和數量）
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.ProductSalesDto(p.id, p.productName, od.price, SUM(od.price * od.quantity), SUM(od.quantity)) " +
            "FROM OrderDetails od " +
            "JOIN od.products p " +
            "GROUP BY p.id, p.productName, od.price "+
            "ORDER BY SUM(od.price * od.quantity) DESC")
    List<ProductSalesDto> findSalesReport();

    // 查詢特定區間的產品銷售數據（包括單價、總銷售額和數量）
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.ProductSalesDto(p.id, p.productName, od.price, SUM(od.price * od.quantity), SUM(od.quantity)) " +
            "FROM OrderDetails od " +
            "JOIN od.products p " +
            "JOIN od.orders o " +
            "WHERE o.createTime BETWEEN :startDateTime AND :endDateTime " +
            "GROUP BY p.id, p.productName, od.price "+
            "ORDER BY SUM(od.price * od.quantity) DESC")
    List<ProductSalesDto> findSalesReportByTimeRange(@Param("startDateTime") LocalDateTime startDateTime,
                                                     @Param("endDateTime") LocalDateTime endDateTime);


    // 查詢所有產品的總營業額
    @Query("SELECT SUM(od.price * od.quantity) FROM OrderDetails od")
    Double findTotalRevenue();

    // 查詢特定區間內的總營業額
    @Query("SELECT SUM(od.price * od.quantity) " +
            "FROM OrderDetails od " +
            "JOIN od.orders o " +
            "WHERE o.createTime BETWEEN :startDateTime AND :endDateTime")
    Double findTotalRevenueByTimeRange(@Param("startDateTime") LocalDateTime startDateTime,
                                       @Param("endDateTime") LocalDateTime endDateTime);
}
