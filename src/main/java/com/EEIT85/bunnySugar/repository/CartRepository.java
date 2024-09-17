package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    //將cart_id對應的購物車每一個清單列出後數量乘以價格並加總
    @Query("SELECT SUM(ci.quantity * ci.price) FROM CartItems ci WHERE ci.cart.id = :cartId")
    Integer calculateTotalPrice(@Param("cartId") Long cartId);

    Cart findByUsersId(Long userId);

}
