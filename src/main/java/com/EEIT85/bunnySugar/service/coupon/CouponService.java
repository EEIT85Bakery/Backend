package com.EEIT85.bunnySugar.service.coupon;

import com.EEIT85.bunnySugar.dto.coupon.CouponBaseDto;
import com.EEIT85.bunnySugar.dto.coupon.CouponInsertDto;
import com.EEIT85.bunnySugar.dto.coupon.CouponUpdateDto;
import com.EEIT85.bunnySugar.entity.Coupon;
import com.EEIT85.bunnySugar.repository.CouponRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository;

    public void insertCoupon(CouponInsertDto couponInsertDto) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponInsertDto.getCouponName());
                coupon.setDiscountNumber(couponInsertDto.getDiscountNumber());
                coupon.setEndDate(couponInsertDto.getEndDate());
                coupon.setEnable(couponInsertDto.getEnable());
                coupon.setCreateTime(LocalDateTime.now());
                coupon.setUpdateTime(LocalDateTime.now());
                coupon.setLeastPriceForDiscount(couponInsertDto.getLeastPriceForDiscount());
        couponRepository.save(coupon);
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    public void updateCoupon(Long id, CouponUpdateDto couponUpdateDto) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coupon with id " + id + " not found"));
        coupon.setCouponName(couponUpdateDto.getCouponName());
        coupon.setDiscountNumber(couponUpdateDto.getDiscountNumber());
        coupon.setEndDate(couponUpdateDto.getEndDate());
        coupon.setEnable(couponUpdateDto.getEnable());
        coupon.setUpdateTime(LocalDateTime.now());
        coupon.setLeastPriceForDiscount(couponUpdateDto.getLeastPriceForDiscount());
        couponRepository.save(coupon);
    }

    public List<Coupon> getCoupons() {
        return couponRepository.findAllWithEnable();
    }

    public Page<Coupon> getCouponsPaginated(Pageable pageable) {
        return couponRepository.findAll(pageable);
    }

    public Page<Coupon> searchCoupons(String search, Pageable pageable) {
        return couponRepository.searchByCouponCode(search, pageable);
    }
}
