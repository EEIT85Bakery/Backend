package com.EEIT85.bunnySugar.service.user;

import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersCartService {

    @Autowired
    private CartRepository cartRepository;  // 注入 CartRepository

    public Cart createCartForUser(Users user) {
        Cart cart = new Cart();
        cart.setUsers(user);  // 設置 Cart 的 Users
        // 設置其他 Cart 的屬性
        cart.setTotal(0);
        cart.setTotalQuantity(0);
        cart.setCreateTime(LocalDateTime.now());
        cart.setUpdateTime(LocalDateTime.now());
        cart = cartRepository.save(cart);  // 使用注入的 cartRepository 來保存 Cart
        return cart;  // return Cart Object
    }


}
