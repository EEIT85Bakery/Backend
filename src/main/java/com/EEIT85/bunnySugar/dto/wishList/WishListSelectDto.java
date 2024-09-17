package com.EEIT85.bunnySugar.dto.wishList;

import java.util.List;

public class WishListSelectDto {

    private Long wishListId;
    private Long userId;
    private List<WishListItemDto> items;

    public WishListSelectDto(Long wishListId, Long userId, List<WishListItemDto> items) {
        this.wishListId = wishListId;
        this.userId = userId;
        this.items = items;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<WishListItemDto> getItems() {
        return items;
    }

    public void setItems(List<WishListItemDto> items) {
        this.items = items;
    }
}
