package com.EEIT85.bunnySugar.controller.orders;


import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.service.orders.admin.OrdersAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/admin/orders")
@RestController
public class OrdersAdminController {


    @Autowired
    OrdersAdminService ordersAdminService;


    @GetMapping
    public ResponseEntity<Page<OrdersFullInfoAdminDto>> getAllOrders(
            @RequestParam(defaultValue = "1") int page,   // 當前的頁碼，預設為第1頁（索引從0開始）
            @RequestParam(defaultValue = "10") int size   // 每頁顯示的資料數量，默認為10條
    ) {
        Pageable pageable = PageRequest.of(page - 1, size); // springboot索引起始為0，實際頁碼起始為1
        Page<OrdersFullInfoAdminDto> ordersPage = ordersAdminService.getAllOrders(pageable);
        return ResponseEntity.ok(ordersPage);
    }


}
