package com.EEIT85.bunnySugar.controller.orders.front;

import com.EEIT85.bunnySugar.dto.orders.front.OrdersFrontDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersInfoDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersInsertDto;
import com.EEIT85.bunnySugar.service.orders.front.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/orders")
@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @PostMapping
    public ResponseEntity<String> addToOrders(HttpServletRequest request, @RequestBody OrdersInsertDto ordersInsertDto) {
        Long userId = (Long) request.getAttribute("userId");
        ordersService.insertOrder(ordersInsertDto, userId);
        return ResponseEntity.ok("成功新增訂單");
    }

    // userId找所有訂單簡介
    @GetMapping
    public ResponseEntity<Page<OrdersInfoDto>> getAllOrdersById(
            HttpServletRequest request,
            @RequestParam(defaultValue = "1") int page,  // 頁碼從1開始
            @RequestParam(defaultValue = "10") int size
    ) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // 如果沒有 userId，回應未授權
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<OrdersInfoDto> ordersPage = ordersService.getAllOrdersByUserId(userId, pageable);

        return ResponseEntity.ok(ordersPage);
    }



    // 前台根據訂單編號查詢訂單及細節
    @GetMapping("/byOrderNumber")
    public ResponseEntity<OrdersFrontDto> getOrderByOrderNumber(
//            HttpServletRequest request,
            @RequestParam String orderNumber) {
//        Long userId = (Long) request.getAttribute("userId");
//        if (userId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
        OrdersFrontDto order = ordersService.getOrderByOrderNumber(orderNumber);
        if (order == null) {
            return ResponseEntity.notFound().build(); // 查無訂單時返回404
        }
        return ResponseEntity.ok(order);
    }
}
