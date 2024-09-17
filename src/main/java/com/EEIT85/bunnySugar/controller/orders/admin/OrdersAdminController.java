package com.EEIT85.bunnySugar.controller.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.admin.OrdersAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.orders.admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.admin.OrdersInfoAdminDto;
import com.EEIT85.bunnySugar.service.orders.admin.OrdersAdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin/orders")
@RestController
public class OrdersAdminController {

    @Autowired
    OrdersAdminService ordersAdminService;

    private static final Logger logger = LoggerFactory.getLogger(OrdersAdminController.class);

    // 查所有會員的所有訂單或是電話號碼/訂單編號查詢
    @GetMapping
    public ResponseEntity<Page<OrdersInfoAdminDto>> getAllOrders(
            HttpServletRequest request,
            @RequestParam(defaultValue = "1") int page,   // 當前頁碼，預設為第1頁（頁碼從1開始）
            @RequestParam(defaultValue = "10") int size,   // 每頁顯示的資料數量，預設為10條
            @RequestParam(required = false) String search
    ) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        logger.info("Search parameter: {}", search);

        // PageRequest.of的頁碼從0開始，所以這裡需要減去1
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getAllOrdersInfo(pageable);
        logger.info("OrdersPage: {}", ordersPage);

        if (search != null && !search.isEmpty()) {
            ordersPage = ordersAdminService.searchOrders(search, pageable);
        } else {
            ordersPage = ordersAdminService.getAllOrdersInfo(pageable);
        }
        return ResponseEntity.ok(ordersPage);
    }


    // 更新取貨或付款狀態
    @PutMapping("/{orderId}/updateStatus")
    public ResponseEntity<String> updateOrderStatus(
            HttpServletRequest request,
            @PathVariable Long orderId,
            @RequestBody OrdersAdminUpdateDto dto) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        ordersAdminService.updateOrderStatus(orderId, dto);
        return ResponseEntity.ok("訂單狀態更新成功");
    }

    @GetMapping("/details/{orderNumber}")
    public ResponseEntity<OrdersFullInfoAdminDto> getOrderDetails(
            @PathVariable String orderNumber,
            HttpServletRequest request) {

        // 確認用戶ID是否存在於請求屬性中，這裡假設需要用戶驗證
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build(); // 未授權
        }

        // 從 service 中查詢訂單詳細信息
        OrdersFullInfoAdminDto orderFullInfo = ordersAdminService.getOrderFullInfoByOrderNumber(orderNumber);

        if (orderFullInfo == null) {
            return ResponseEntity.status(404).build(); // 找不到訂單
        }

        // 返回查詢到的訂單詳細信息
        return ResponseEntity.ok(orderFullInfo);
    }

    // 刪除會員
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrderById(
            HttpServletRequest request,  // 確保用戶已登入
            @PathVariable Long orderId) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("未授權的操作");
        }
        boolean success = ordersAdminService.deleteOrderById(orderId);
        if (success) {
            return ResponseEntity.ok("會員刪除成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員不存在");
        }
    }


//    // 根據電話號碼查詢訂單
//    @GetMapping("/byPhone")
//    public ResponseEntity<Page<OrdersInfoAdminDto>> getOrdersByUserPhone(
//            HttpServletRequest request,
//            @RequestParam String phone,                    // 會員電話
//            @RequestParam(defaultValue = "1") int page,    // 當前頁碼，預設為第1頁
//            @RequestParam(defaultValue = "10") int size    // 每頁顯示的資料數量，預設為10條
//    ) {
//        Long userId = (Long) request.getAttribute("userId");
//        if (userId == null) {
//            return ResponseEntity.status(401).build();
//        }
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getOrdersByUserPhone(phone, pageable);
//        return ResponseEntity.ok(ordersPage);
//    }

//     // 根據訂單編號查詢訂單
//    @GetMapping("/byOrderNumber")
//    public ResponseEntity<Page<OrdersInfoAdminDto>> getOrdersByOrderNumber(
//            HttpServletRequest request,
//            @RequestParam String orderNumber,
//            @RequestParam(defaultValue = "1") int page,    // 當前頁碼，預設為第1頁
//            @RequestParam(defaultValue = "10") int size    // 每頁顯示的資料數量，預設為10條
//    ) {
//        Long userId = (Long) request.getAttribute("userId");
//        if (userId == null) {
//            return ResponseEntity.status(401).build();
//        }
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getOrdersByUserPhone(orderNumber, pageable);
//        return ResponseEntity.ok(ordersPage);
//    }

//    @GetMapping("/details")
//    public ResponseEntity<OrdersFullInfoAdminDto> getOrderDetailsByOrderId(
//            HttpServletRequest request,
//            @RequestParam Long orderId,
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Long userId = (Long) request.getAttribute("userId");
//        if (userId == null) {
//            return ResponseEntity.status(401).build();
//        }
//        if (page < 1) {
//            page = 1;  // 如果頁碼小於1，則設置為1
//        }
//        Pageable pageable = PageRequest.of(page - 1, size);
//        OrdersFullInfoAdminDto orderDetails = ordersAdminService.getOrderFullInfoByOrderId(orderId, pageable);
//
//        if (orderDetails == null) {
//            return ResponseEntity.notFound().build(); // 查無資料返回404
//        }
//        return ResponseEntity.ok(orderDetails);
//    }
}
