package com.EEIT85.bunnySugar.controller.cart;

import com.EEIT85.bunnySugar.dto.cart.CartInsertDto;
import com.EEIT85.bunnySugar.dto.cart.CartSelectDto;
import com.EEIT85.bunnySugar.dto.cart.CartUpdateDto;
import com.EEIT85.bunnySugar.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/cart") // 在这里添加 '/api' 前缀
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartSelectDto>> getCartItems(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<CartSelectDto> cartItems = cartService.getCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }


    @PostMapping
    public ResponseEntity<String> addToCart(HttpServletRequest request, @RequestBody CartInsertDto cartInsertDto) {
        Long userId = (Long) request.getAttribute("userId");
        cartService.insertCart(cartInsertDto, userId);
        return ResponseEntity.ok("成功新增購物車");
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteCartItem(HttpServletRequest request, @PathVariable Long itemId) {
        Long userId = (Long) request.getAttribute("userId");
        cartService.deleteCartItem(userId, itemId);
        return ResponseEntity.ok("成功刪除單一購物車品項");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCartItems(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        cartService.deleteAllCartItems(userId);
        return ResponseEntity.ok("成功清空購物車");
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<String> updateCartItem(HttpServletRequest request,
                                                 @PathVariable Long cartItemId,
                                                 @RequestBody CartUpdateDto cartUpdateDto) {
        Long userId = (Long) request.getAttribute("userId");
        cartService.updateCartItem(cartItemId, cartUpdateDto);
        return ResponseEntity.ok("Cart item updated successfully");
    }
}
