package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("SELECT c FROM Coupon c WHERE (:search IS NULL) OR " +
            "LOWER(c.couponName) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Coupon> searchByCouponCode(@Param("search") String search, Pageable pageable);

    @Query("SELECT c FROM Coupon c WHERE c.enable = true")
    List<Coupon> findAllWithEnable();
}
