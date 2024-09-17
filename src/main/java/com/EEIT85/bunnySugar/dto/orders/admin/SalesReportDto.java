package com.EEIT85.bunnySugar.dto.orders.admin;

import java.util.List;

public class SalesReportDto {
    private List<ProductSalesDto> productSalesDto; // 每個產品的銷售數據
    private Double totalRevenue;               // 所有產品的總營業額

    public SalesReportDto() {
    }

    public SalesReportDto(List<ProductSalesDto> productSalesDto, Double totalRevenue) {
        this.productSalesDto = productSalesDto;
        this.totalRevenue = totalRevenue;
    }

    public List<ProductSalesDto> getProductSalesDto() {
        return productSalesDto;
    }

    public void setProductSalesDto(List<ProductSalesDto> productSalesDto) {
        this.productSalesDto = productSalesDto;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}

