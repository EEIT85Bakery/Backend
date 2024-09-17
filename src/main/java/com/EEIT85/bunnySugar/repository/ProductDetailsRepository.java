package com.EEIT85.bunnySugar.repository;


import com.EEIT85.bunnySugar.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails,
        Long> {
    Optional<ProductDetails> findByProductsId(Long id);
}
