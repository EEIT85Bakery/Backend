package com.EEIT85.bunnySugar.controller.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto;
import com.EEIT85.bunnySugar.service.orders.admin.SalesReportService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequestMapping("/api/admin/sales")
@RestController
public class SalesReportController {
    @Autowired
    private SalesReportService salesReportService;

    // 取得銷售報表數據
    @GetMapping()
    public SalesReportDto getSalesReport(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return salesReportService.getSalesReport();
    }

    // 指定區間銷售數據
    @GetMapping("/dateRange")
    public SalesReportDto getSalesReportByDateRange(HttpServletRequest request,
                                                    @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
                                                    @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate) {
        Long userId = (Long) request.getAttribute("userId");

        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        return salesReportService.getSalesReportByTimeRange(startDate, endDate);
    }
}