package com.EEIT85.bunnySugar.service.products.admin;

import com.EEIT85.bunnySugar.dto.products.ProductsAdminBaseDto;
import com.EEIT85.bunnySugar.entity.Categories;
import com.EEIT85.bunnySugar.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class FindOrCreateCategoryIdService {




        @Autowired
        private CategoriesRepository categoriesRepository;

    public Long findOrCreateAndUpdateCategoryId(ProductsAdminBaseDto productsAdminBaseDto) {
        // 依照 CategoryName 及 flavor 找出資料庫中的種類
        Categories categories = categoriesRepository.findByCategoryNameAndFlavor(
                productsAdminBaseDto.getCategoryName(),
                productsAdminBaseDto.getFlavor()
        );

        if (categories == null) {
            categories = new Categories();
            categories.setCreateTime(LocalDateTime.now());
        }

        // 無論是新建還是已存在的類別，都更新這些字段
        categories.setCategoryName(productsAdminBaseDto.getCategoryName());
        categories.setFlavor(productsAdminBaseDto.getFlavor());

        // 只有在提供了新的描述時才更新描述字段
        if (productsAdminBaseDto.getCategoryDescription() != null) {
            categories.setCategoryDescription(productsAdminBaseDto.getCategoryDescription());
        }

        categories.setUpdateTime(LocalDateTime.now());

        Categories savedCategories = categoriesRepository.save(categories);

        // 回傳該種類的 ID
        return savedCategories.getId();
    }


}

