package com.EEIT85.bunnySugar.dto.cart;


public class CartUpdateDto {
    private Integer quantity;

    public CartUpdateDto() {
    }

    public CartUpdateDto( Integer quantity) {
        this.quantity = quantity;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
