package com.EEIT85.bunnySugar.dto.orders.admin;

public class ProductSalesDto {
    private Long productId;
    private String productName;
    private Integer price;  // 商品單價
    private Long sumQuantity;  // 商品總銷售數量
    private Long totalSales;    // 單個商品的銷售額

    // 無參數構造函數
    public ProductSalesDto() {
    }

    // 有參數構造函數
    public ProductSalesDto(Long productId, String productName, Integer price, Long totalSales, Long sumQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.sumQuantity = sumQuantity;
        this.totalSales = totalSales;
    }

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(Long sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

    public Long getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Long totalSales) {
        this.totalSales = totalSales;
    }
}
