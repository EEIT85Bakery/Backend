package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,
        Long> {
    // 透過產品名稱進行模糊查詢
    Page<Products> findByProductNameContaining(String productName, Pageable pageable);

//    @Query("SELECT p.id FROM Product p WHERE p.productName = :productName")
//    Long findIdByProductName(@Param("productName") String productName);

    Products findByProductName(String productName);

    @Query("SELECT p FROM Products p JOIN p.categories c WHERE c.categoryName = :categoryName")
    Page<Products> findProductsByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

    @Query("SELECT p FROM Products p JOIN p.categories c WHERE c.flavor = :flavor")
    Page<Products> findProductsByFlavor(@Param("flavor") String flavor, Pageable pageable);


}
