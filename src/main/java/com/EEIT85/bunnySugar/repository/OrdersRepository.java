package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsFrontDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersFrontDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersInfoDto;
import com.EEIT85.bunnySugar.dto.orders.admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.admin.OrdersInfoAdminDto;
import com.EEIT85.bunnySugar.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {


    // 訂單後台查詢全部訂單基本資訊，(不含商品細節)
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.OrdersInfoAdminDto(o.id, o.orderNumber, u.name, u.phone, pd.paidPrice,  " +
            "pd.paymentStatus, o.pickupStatus) " +
            "FROM Orders o " +
            "JOIN o.user u " +
            "JOIN o.paymentDetails pd")
    Page<OrdersInfoAdminDto> findAllOrdersInfo(Pageable pageable);

//    // 訂單或是電話模糊查詢(sql)
//    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.Admin.OrdersInfoAdminDto(o.orderNumber, u.name, u.phone, pd.paidPrice, " +
//            "pd.paymentStatus, o.pickupStatus) " +
//            "FROM Orders o " +
//            "JOIN o.user u " +
//            "JOIN o.paymentDetails pd " +
//            "WHERE o.orderNumber LIKE %:search% OR u.phone LIKE %:search%")
//    Page<OrdersInfoAdminDto> findOrdersInfoByOrderNumberOrUserPhone(@Param("search") String search, Pageable pageable);

    // 訂單編號或是電話模糊查詢(jpql)
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.OrdersInfoAdminDto(o.id, o.orderNumber, u.name, u.phone, pd.paidPrice, " +
            "pd.paymentStatus, o.pickupStatus) " +
            "FROM Orders o " +
            "JOIN o.user u " +
            "JOIN o.paymentDetails pd " +
            "WHERE o.orderNumber LIKE CONCAT('%', :search, '%') OR u.phone LIKE CONCAT('%', :search, '%')")
    Page<OrdersInfoAdminDto> findOrdersInfoByOrderNumberOrUserPhone(@Param("search") String search, Pageable pageable);


    // 訂單後台根據會員電話查詢訂單(不含商品細節)
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.OrdersInfoAdminDto(o.id, o.orderNumber, u.name, u.phone, pd.paidPrice,  " +
            "pd.paymentStatus, o.pickupStatus) " +
            "FROM Orders o " +
            "JOIN o.user u " +
            "JOIN o.paymentDetails pd " +
            "WHERE u.phone LIKE :phone")
    Page<OrdersInfoAdminDto> findOrdersInfoByUserPhone(@Param("phone") String phone, Pageable pageable);

    // 訂單後台根據orderNumber查詢訂單(不含商品細節)
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.OrdersInfoAdminDto(o.id, o.orderNumber, u.name, u.phone, pd.paidPrice,  " +
            "pd.paymentStatus, o.pickupStatus) " +
            "FROM Orders o " +
            "JOIN o.user u " +
            "JOIN o.paymentDetails pd " +
            "WHERE o.orderNumber LIKE :orderNumber")
    OrdersInfoAdminDto findOrdersInfoByOrderNumber(@Param("orderNumber") String orderNumber);


    // 訂單後台編輯，跳出根據orderNumber查詢訂單的詳細資
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.OrdersFullInfoAdminDto(o.id, o.orderNumber, u.name, u.phone, u.email, o.createTime, " +
            "pd.paymentStatus, o.pickupStatus, pd.paidPrice, pd.paymentMethod, pd.paymentDate, o.total, " +
            "o.couponName, o.usedBunnyCoins, o.pickupTime, u.gameTimes) " +
            "FROM Orders o " +
            "JOIN o.user u " +
            "JOIN o.paymentDetails pd " +
            "WHERE o.orderNumber = :orderNumber")
    OrdersFullInfoAdminDto findOrderFullInfoByOrderNumber(@Param("orderNumber") String orderNumber);

    // 訂單前後台根據orderNumber查詢指定訂單中的 OrderDetails(很多商品)
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsFrontDto(p.productName, od.quantity, od.price, pd.img1, (od.quantity * od.price)) " +
            "FROM OrderDetails od JOIN od.products p JOIN p.productDetails pd JOIN od.orders o WHERE o.orderNumber = :orderNumber")
    List<OrderDetailsFrontDto> findOrderDetailsByOrderNumber(@Param("orderNumber") String orderNumber);


    // 訂單後台更新訂單狀態
    @Modifying
    @Query("UPDATE Orders o SET o.pickupStatus = :pickupStatus , o.paymentDetails.paymentStatus = :paymentStatus WHERE o.id = :orderId")
    int updatePickupStatus(@Param("orderId") Long orderId, @Param("pickupStatus") String pickupStatus, @Param("pickupStatus") String paymentStatus);

    // 訂單前台根據 userId 查詢訂單簡略資料(不含商品細節)
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.front.OrdersInfoDto(o.orderNumber, o.createTime, pd.paidPrice, o.pickupStatus) " +
            "FROM Orders o " +
            "JOIN o.user u " +
            "JOIN o.paymentDetails pd " +
            "WHERE u.id = :userId")
    Page<OrdersInfoDto> findAllOrdersByUserId(@Param("userId") Long userId, Pageable pageable);

    // 前台根據訂單編號查詢訂單資訊(不含商品細節)
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.front.OrdersFrontDto(o.orderNumber, o.createTime, pd.paymentMethod, pd.paymentStatus, o.pickupStatus, " +
            "o.pickupTime, o.total, o.couponName, o.usedBunnyCoins, pd.paidPrice, u.name, u.phone, u.email) " +
            "FROM Orders o " +
            "JOIN o.user u " +
            "JOIN o.paymentDetails pd " +
            "WHERE o.orderNumber = :orderNumber")
    OrdersFrontDto findFrontOrderByOrderNumber(@Param("orderNumber") String orderNumber);

}
