package com.EEIT85.bunnySugar.service.products.front;

import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.repository.CategoriesRepository;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

      // 封裝將 Products 轉換為 ProductsSelectDto 的方法
    private ProductsSelectDto convertToDto(Products product) {
        ProductsSelectDto productsSelectDto = new ProductsSelectDto();
        productsSelectDto.setId(product.getId());
        productsSelectDto.setProductName(product.getProductName());
        productsSelectDto.setStocks(product.getStocks());
        productsSelectDto.setDescription(product.getProductDetails().getDescription());
        productsSelectDto.setPrice(product.getProductDetails().getPrice());
        productsSelectDto.setMaterialDescription(product.getProductDetails().getMaterialDescription());
        productsSelectDto.setImg1(product.getProductDetails().getImg1());
        productsSelectDto.setImg2(product.getProductDetails().getImg2());
        productsSelectDto.setImg3(product.getProductDetails().getImg3());
        productsSelectDto.setImg4(product.getProductDetails().getImg4());
        productsSelectDto.setCategoryName(product.getCategories().getCategoryName());
        productsSelectDto.setFlavor(product.getCategories().getFlavor());
        productsSelectDto.setCategoryDescription(product.getCategories().getCategoryDescription());
        return productsSelectDto;
    }

    // 取回所有產品，並將其轉換為 DTO
    public Page<ProductsSelectDto> getAll(Pageable pageable, String sort) {
        Pageable sortedPageable = createSortedPageable(pageable, sort);
        Page<Products> productsPage = productsRepository.findAllEnabledProducts(sortedPageable);
        return productsPage.map(this::convertToDto);
    }

    // 根據 ID 查詢產品，並轉換為 DTO
    public ProductsSelectDto getById(Long id) {
        Optional<Products> findProduct = productsRepository.findById(id);
        if (findProduct.isEmpty()) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        Products product = findProduct.get();
        return convertToDto(product);  // 使用封裝的方法進行轉換
    }

    // 獲取所有的 category 名稱
    public Set<String> getAllCategoryNames() {
        List<String> categoryNames = categoriesRepository.findAllEnabledCategoryNames();
        if (categoryNames.isEmpty()) {
            throw new ResourceNotFoundException("沒有此種類");
        }
        // 有序不重複
        return new LinkedHashSet<>(categoryNames);
    }

    // 查詢對應 categoryName 的所有風味名稱
    public Set<String> getFlavorsByCategoryName(String categoryName) {
        List<String> flavors = categoriesRepository.findFlavorsByCategoryName(categoryName);
        if (flavors.isEmpty()) {
            throw new ResourceNotFoundException("沒有找到相對應風味的商品");
        }
        // 有序不重複
        return new LinkedHashSet<>(flavors);
    }

    private Pageable createSortedPageable(Pageable pageable, String sort) {
        if (sort != null && sort.equalsIgnoreCase("createTime")) {
            return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "createTime"));
        }
        // 如果排序參數為空，則返回默認的 pageable
        return pageable;
    }


    // 根據category名稱查詢商品
    public Page<ProductsSelectDto> getProductsByCategoryName(String categoryName, Pageable pageable, String sort) {
        Pageable sortedPageable = createSortedPageable(pageable, sort);
        Page<Products> productsPage = productsRepository.findProductsByCategoryName(categoryName, sortedPageable);
        return productsPage.map(this::convertToDto);
    }

    // 根據flavor名稱查詢商品
    public Page<ProductsSelectDto> getProductsByFlavor(String flavor, Pageable pageable, String sort) {
        Pageable sortedPageable = createSortedPageable(pageable, sort);
        Page<Products> productsPage = productsRepository.findProductsByFlavor(flavor, sortedPageable);
        return productsPage.map(this::convertToDto);
    }

    // 透過產品名稱模糊查詢
    public Page<ProductsSelectDto> searchProductsByNameLike(String keyword, Pageable pageable, String sort) {
        Pageable sortedPageable = createSortedPageable(pageable, sort);
        Page<Products> productsPage = productsRepository.findByProductNameContaining(keyword, sortedPageable);
        return productsPage.map(this::convertToDto);
    }
}
