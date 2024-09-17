package com.EEIT85.bunnySugar.service.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.admin.OrdersAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.orders.admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.admin.OrdersInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsFrontDto;
import com.EEIT85.bunnySugar.entity.Orders;
import com.EEIT85.bunnySugar.entity.PaymentDetails;
import com.EEIT85.bunnySugar.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersAdminService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    // 訂單後台查詢全部訂單基本資訊，(不含商品細節)
    public Page<OrdersInfoAdminDto> getAllOrdersInfo(Pageable pageable) {
        // 將查詢訂單的順序改為依據 createTime 降序排列
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createTime"));
        Page<OrdersInfoAdminDto> ordersPage = ordersRepository.findAllOrdersInfo(sortedPageable);
        // 查詢訂單的商品細節
        ordersPage.forEach(order -> {
            List<OrderDetailsFrontDto> orderDetails = ordersRepository.findOrderDetailsByOrderNumber(order.getOrderNumber());
            order.setOrderDetails(orderDetails);
        });
        return ordersPage;
    }

    // 訂單編號與電話號碼查詢訂單(不含商品細節)
    public Page<OrdersInfoAdminDto> searchOrders(String search, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createTime"));
        Page<OrdersInfoAdminDto> ordersPage = ordersRepository.findOrdersInfoByOrderNumberOrUserPhone(search, sortedPageable);
        // 查詢訂單的商品細節
        ordersPage.forEach(order -> {
            List<OrderDetailsFrontDto> orderDetails = ordersRepository.findOrderDetailsByOrderNumber(order.getOrderNumber());
            order.setOrderDetails(orderDetails);
        });
        return ordersPage;
    }

//    // 訂單後台根據會員電話查詢訂單(不含商品細節)
//    public Page<OrdersInfoAdminDto> getOrdersByUserPhone(String phone, Pageable pageable) {
//        Page<OrdersInfoAdminDto> ordersPage = ordersRepository.findOrdersInfoByUserPhone(phone, pageable);
//        // 查詢訂單的商品細節
//        ordersPage.forEach(order -> {
//            List<OrderDetailsFrontDto> orderDetails = ordersRepository.findOrderDetailsByOrderNumber(order.getOrderNumber());
//            order.setOrderDetails(orderDetails);
//        });
//        return ordersPage;
//    }
//
//    // 訂單後台根據orderNumber查詢訂單(不含商品細節)
//    public OrdersInfoAdminDto getOrdersByOrderNumber(String orderNumber) {
//        OrdersInfoAdminDto orderInfo = ordersRepository.findOrdersInfoByOrderNumber(orderNumber);
//        return orderInfo;
//    }

    // 訂單後台編輯，跳出根據orderNumber查詢訂單的詳細資
    public OrdersFullInfoAdminDto getOrderFullInfoByOrderNumber(String orderNumber) {
        // 直接使用 OrdersRepository 的查詢方法
        OrdersFullInfoAdminDto orderFullInfo = ordersRepository.findOrderFullInfoByOrderNumber(orderNumber);
        // 查詢訂單的商品細節
        List<OrderDetailsFrontDto> orderDetails = ordersRepository.findOrderDetailsByOrderNumber(orderNumber);
        orderFullInfo.setOrderDetails(orderDetails);
        return orderFullInfo;
    }

    // 更新取貨或付款狀態，保持未修改的欄位原有狀態
    @Transactional
    public void updateOrderStatus(Long orderId, OrdersAdminUpdateDto dto) {
        // 查詢訂單及其相關的付款信息
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("訂單未找到"));
        // 更新取貨狀態，保持未修改欄位的原狀態
        if (dto.getPickupStatus() != null) {
            order.setPickupStatus(dto.getPickupStatus());
        }
        // 獲取訂單的付款詳情並更新付款狀態
        PaymentDetails paymentDetails = order.getPaymentDetails();
        if (dto.getPaymentStatus() != null) {
            paymentDetails.setPaymentStatus(dto.getPaymentStatus());
        }
        // 保存更新後的訂單和付款詳情
        ordersRepository.save(order);
        paymentDetailsRepository.save(paymentDetails);
    }

    //訂單後台刪除訂單
    public boolean deleteOrderById(Long id) {
        return ordersRepository.findById(id).map(order -> {
            ordersRepository.deleteById(id);
            return true;
        }).orElse(false);
    }


}
