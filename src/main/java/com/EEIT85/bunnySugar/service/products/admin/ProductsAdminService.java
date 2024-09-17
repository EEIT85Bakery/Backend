package com.EEIT85.bunnySugar.service.products.admin;

import com.EEIT85.bunnySugar.dto.products.*;
import com.EEIT85.bunnySugar.entity.Categories;
import com.EEIT85.bunnySugar.entity.ProductDetails;
import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.repository.CategoriesRepository;
import com.EEIT85.bunnySugar.repository.ProductDetailsRepository;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductsAdminService {

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Autowired
    FindOrCreateCategoryIdService findOrCreateCategoryIdService;

    public Products insertProducts(ProductsInsertDto dto) {
        // 檢查並獲取或創建Categories
        Categories categories = categoriesRepository
                .findByCategoryNameAndFlavor(dto.getCategoryName(), dto.getFlavor());

        if (categories == null) {
            categories = new Categories();
            categories.setCategoryName(dto.getCategoryName());
            categories.setFlavor(dto.getFlavor());
            categories.setCategoryDescription(dto.getCategoryDescription());
            categories.setCreateTime(LocalDateTime.now());
            categories.setUpdateTime(LocalDateTime.now());
            categories = categoriesRepository.save(categories);
        }

        // 創建Products
        Products products = new Products();
        products.setProductName(dto.getProductName());
        products.setStocks(dto.getStocks());
        products.setCategories(categories);
        products.setCreateTime(LocalDateTime.now());
        products.setUpdateTime(LocalDateTime.now());

        // 創建ProductDetails
        ProductDetails details = new ProductDetails();
        details.setProducts(products);
        details.setDescription(dto.getDescription());
        details.setPrice(dto.getPrice());
        details.setMaterialDescription(dto.getMaterialDescription());
        details.setEnable(dto.getEnable());
        details.setImg1(dto.getImg1());
        details.setImg2(dto.getImg2());
        details.setImg3(dto.getImg3());
        details.setImg4(dto.getImg4());
        details.setCreateTime(LocalDateTime.now());
        details.setUpdateTime(LocalDateTime.now());

        products.setProductDetails(details);
        return productsRepository.save(products);
    }

    public Products updateProduct(Long id, ProductsUpdateDto dto) {
        Products products =
                productsRepository.findProductWithDetailsById(id);
        if (products == null) {
            throw new RuntimeException("Product not found");
        }

        // 如果有新的分類信息，檢查並獲取或創建Categories
        if (dto.getCategoryName() != null && dto.getFlavor() != null) {
            Categories categories = categoriesRepository
                    .findByCategoryNameAndFlavor(dto.getCategoryName(), dto.getFlavor());

            if (categories == null) {
                categories = new Categories();
                categories.setCategoryName(dto.getCategoryName());
                categories.setFlavor(dto.getFlavor());
                categories.setCategoryDescription(dto.getCategoryDescription());
                categories.setCreateTime(LocalDateTime.now());
                categories.setUpdateTime(LocalDateTime.now());
                categories = categoriesRepository.save(categories);
            } else if (dto.getCategoryDescription() != null) {
                // 更新現有分類的描述
                categories.setCategoryDescription(dto.getCategoryDescription());
                categories.setUpdateTime(LocalDateTime.now());
                categories = categoriesRepository.save(categories);
            }
            products.setCategories(categories);
        }

        // 更新Products
        if (dto.getProductName() != null) {
            products.setProductName(dto.getProductName());
        }
        if (dto.getStocks() != null) {
            products.setStocks(dto.getStocks());
        }
        products.setUpdateTime(LocalDateTime.now());

        // 更新ProductDetails
        ProductDetails details = products.getProductDetails();
        if (dto.getDescription() != null) {
            details.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            details.setPrice(dto.getPrice());
        }
        if (dto.getMaterialDescription() != null) {
            details.setMaterialDescription(dto.getMaterialDescription());
        }
        if (dto.getEnable() != null) {
            details.setEnable(dto.getEnable());
        }
        if (!Objects.equals(dto.getImg1(), "")) {
            details.setImg1(dto.getImg1());
        }
        if (!Objects.equals(dto.getImg2(), "")) {
            details.setImg2(dto.getImg2());
        }
        if (!Objects.equals(dto.getImg3(), "")) {
            details.setImg3(dto.getImg3());
        }
        if (!Objects.equals(dto.getImg4(), "")) {
            details.setImg4(dto.getImg4());
        }
        details.setUpdateTime(LocalDateTime.now());

        return productsRepository.save(products);
    }


    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!productsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productsRepository.deleteById(id);
    }


    public List<ProductsAdminSelectDto> getAll() {
        return productsRepository.getAdminAllProducts();
    }

    public Page<ProductsAdminSelectDto> getAllPaginated(Pageable pageable) {
        return productsRepository.getAdminAllProductsPaginated(pageable);
    }

    public Page<ProductsAdminSelectDto> searchProducts(String search, Pageable pageable) {
        return productsRepository.searchProducts(search, pageable);
    }

}