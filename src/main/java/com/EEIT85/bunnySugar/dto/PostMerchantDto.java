package com.EEIT85.bunnySugar.dto;

public class PostMerchantDto {
    private Integer total;
    private String merchantNo;

    public PostMerchantDto() {
    }

    public PostMerchantDto(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}