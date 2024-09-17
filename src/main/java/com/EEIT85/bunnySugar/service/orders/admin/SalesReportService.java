package com.EEIT85.bunnySugar.service.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.admin.ProductSalesDto;
import com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto;
import com.EEIT85.bunnySugar.repository.SalesReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class SalesReportService {

    @Autowired
    private SalesReportRepository salesReportRepository;

    // 獲取產品銷售數據和總營業額
    public SalesReportDto getSalesReport() {
        // 查詢每個產品的銷售數據
        List<ProductSalesDto> productSales = salesReportRepository.findSalesReport();

        // 查詢所有產品的總營業額
        Double totalRevenue = salesReportRepository.findTotalRevenue();

        // 組合數據並返回
        return new SalesReportDto(productSales, totalRevenue);
    }

    // 獲取特定區間的產品銷售數據和總營業額
    public SalesReportDto getSalesReportByTimeRange(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay(); // 開始日期的起始時間
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX); // 結束日期的結束時間

        List<ProductSalesDto> productSales = salesReportRepository.findSalesReportByTimeRange(startDateTime, endDateTime);
        Double totalRevenue = salesReportRepository.findTotalRevenueByTimeRange(startDateTime, endDateTime);

        // 組合數據並返回
        return new SalesReportDto(productSales, totalRevenue);
    }
}