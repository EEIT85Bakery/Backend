package com.EEIT85.bunnySugar.repository;


import com.EEIT85.bunnySugar.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,
        Long> {

    @Query("SELECT c FROM Categories c WHERE c.categoryName = :categoryName AND" +
            " c" +
            ".flavor = :flavor")
    Categories findByCategoryNameAndFlavor(@Param("categoryName") String categoryName,
                                           @Param("flavor") String flavor);

    @Query("SELECT DISTINCT c.categoryName FROM Categories c JOIN c.products p JOIN p.productDetails pd WHERE pd.enable = true")
    List<String> findAllEnabledCategoryNames();

    @Query("SELECT DISTINCT c.flavor FROM Categories c JOIN c.products p JOIN p.productDetails pd WHERE pd.enable = true AND c.categoryName = :categoryName")
    List<String> findFlavorsByCategoryName(@Param("categoryName") String categoryName);

}