package com.EEIT85.bunnySugar.service.cart;

import com.EEIT85.bunnySugar.dto.cart.CartInsertDto;
import com.EEIT85.bunnySugar.dto.cart.CartSelectDto;
import com.EEIT85.bunnySugar.dto.cart.CartUpdateDto;
import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.CartItems;
import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.repository.CartItemsRepository;
import com.EEIT85.bunnySugar.repository.CartRepository;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    @Autowired
    CartItemsRepository cartItemsRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductsRepository productsRepository;



    public List<CartSelectDto> getCartItemsByUserId(Long userId) {
        return cartItemsRepository.findCartItemsByUserId(userId);
    }


    @Transactional
    public void insertCart(CartInsertDto cartInsertDto, Long userId) {


        //用dto中的userId找出他在資料庫中對應的購物車
        Cart cart = cartRepository.findByUsersId(userId);
        System.out.println(cart);
        //用dto中的productId找出對應的Product
        Products products =
                productsRepository.findById(cartInsertDto.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        //用products_id資訊查是否有這個CartItem
        Optional<CartItems> existingCartItem =
                cartItemsRepository.findByProductsIdAndCartId(products.getId(),
                        cart.getId());
        //如果有，修改後存入 若沒有．新建一個購物清單實體 準備存入資料庫
        if (existingCartItem.isPresent()) {
            CartItems cartItem = existingCartItem.get();
            if (cartItem.getProducts().getStocks() >= cartItem.getQuantity() + cartInsertDto.getQuantity()) {
                cartItem.setQuantity(cartItem.getQuantity() + cartInsertDto.getQuantity());
            }
            cartItem.setPrice(cartInsertDto.getPrice()); // 更新價格，如果需要的話
            cartItem.setUpdateTime(LocalDateTime.now());
            cartItemsRepository.save(cartItem);
        } else {
            // 創建新的CartItems 並用自訂建構式處理存入
            CartItems newCartItem = new CartItems(
                    cart,
                    products,
                    cartInsertDto.getQuantity(),
                    cartInsertDto.getPrice(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            cartItemsRepository.save(newCartItem);
        }
        //如果productId存在 改為update 並增加quantity

        //修改購物車中的金額與更新時間
        cart.setUpdateTime(LocalDateTime.now());
        //更新cart中的total
        cart.setTotal(cartRepository.calculateTotalPrice(cart.getId()));
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteCartItem(Long userId, Long itemId) {
        CartItems cartItem = cartItemsRepository.findByIdAndCart_Users_Id(itemId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("購物車品項不存在或不屬於該用戶"));
        cartItemsRepository.delete(cartItem);
        //取得cartId
        Long cartId = cartItem.getCart().getId();
        //用cart中的關聯users_id找到cart
        Cart cart = cartRepository.findByUsersId(userId);
        //刪除後要重新計算
        Integer newTotal = cartRepository.calculateTotalPrice(cartId);
        cart.setTotal(newTotal);
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteAllCartItems(Long usersId) {
        Cart cart = cartRepository.findByUsersId(usersId);
        cartItemsRepository.deleteByCartId(cart.getId());
        //清空購物車後把cart的total歸零
        cart.setUpdateTime(LocalDateTime.now());
        cart.setTotal(0);
        cart.setTotalQuantity(0);
        cartRepository.save(cart);
    }

    @Transactional
    public void updateCartItem(Long cartItemId, CartUpdateDto cartUpdateDto) {
        CartItems cartItem = cartItemsRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        cartItem.setQuantity(cartUpdateDto.getQuantity());
        cartItem.setUpdateTime(LocalDateTime.now());
        cartItemsRepository.save(cartItem);

        // Update the cart's total price
        Cart cart = cartItem.getCart();
        cart.setTotal(cartRepository.calculateTotalPrice(cart.getId()));
        cart.setUpdateTime(LocalDateTime.now());
        cartRepository.save(cart);
    }


}