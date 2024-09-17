package com.EEIT85.bunnySugar.dto.wishList;

import java.util.List;

public class WishListInsertDto {

//    private Long userId;
    private Long productId;

    public WishListInsertDto() {
    }

    public WishListInsertDto(Long productId) {
//        this.userId = userId;
        this.productId = productId;
    }

//    public Long getUserId() {
//        return userId;
//    }

//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
