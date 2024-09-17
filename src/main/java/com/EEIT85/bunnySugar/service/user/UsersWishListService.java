package com.EEIT85.bunnySugar.service.user;

import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.entity.WishList;
import com.EEIT85.bunnySugar.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersWishListService {

    @Autowired
    private WishListRepository wishListRepository;

    public WishList createWishListForUser(Users user) {
        WishList wishList = new WishList();
        wishList.setUsers(user);  // 設置 WishList 的 Users
        // 設置其他 WishList 的屬性
        wishList.setCreateTime(LocalDateTime.now());
        wishList.setUpdateTime(LocalDateTime.now());
        wishList = wishListRepository.save(wishList);  // 使用注入的 wishListRepository 來保存 WishList
        return wishList;  // return WishList Object
    }

}
