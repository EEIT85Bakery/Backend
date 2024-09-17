package com.EEIT85.bunnySugar.service.wishListService;

import com.EEIT85.bunnySugar.dto.wishList.WishListInsertDto;
import com.EEIT85.bunnySugar.dto.wishList.WishListItemDto;
import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.entity.WishList;
import com.EEIT85.bunnySugar.entity.WishListItems;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import com.EEIT85.bunnySugar.repository.WishListItemsRepository;
import com.EEIT85.bunnySugar.repository.WishListRepository;
import com.EEIT85.bunnySugar.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    WishListItemsRepository wishListItemsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductsRepository productsRepository;


    // 查找用戶的收藏清單，支持分頁
    public Page<WishListItemDto> getWishListItemsByUserId(Long userId, Pageable pageable) {
        return wishListItemsRepository.findWishListItemsByUserId(userId, pageable);
    }

    @Transactional
    public void addProductToWishList(WishListInsertDto wishListInsertDto, Long userId) {
        // 根據用戶ID查詢收藏清單
        Optional<WishList> wishListOptional = wishListRepository.findByUsersId(userId);

        if (wishListOptional.isPresent()) {
            WishList wishList = wishListOptional.get();
            Long productId = wishListInsertDto.getProductId();

            // 檢查商品是否已經存在於收藏清單中
            Optional<WishListItems> existingItem = wishListItemsRepository
                    .findByUserIdAndProductId(wishList.getUsers().getId(), productId);

            if (existingItem.isEmpty()) {
                // 若不存在則新增
                Products product = productsRepository.findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

                WishListItems newItem = new WishListItems();
                newItem.setWishList(wishList);
                newItem.setProducts(product);
                newItem.setCreateTime(LocalDateTime.now());
                newItem.setUpdateTime(LocalDateTime.now());

                wishListItemsRepository.save(newItem);
            } else {
                throw new RuntimeException("該商品已經在收藏清單中");
            }
        } else {
            throw new ResourceNotFoundException("Wish List not found for user ID: " + userId);
        }
    }

    @Transactional
    public void deleteProductFromWishList(Long userId, Long productId) {

        // 根據用戶ID查詢收藏清單
        Optional<WishList> wishListOptional = wishListRepository.findByUsersId(userId);

        if (wishListOptional.isPresent()) {
            WishList wishList = wishListOptional.get();

            // 刪除商品
            wishListItemsRepository.deleteByWishListIdAndProductsId(wishList.getId(), productId);
        } else {
            throw new ResourceNotFoundException("Wish List not found for user ID: " + userId);
        }

    }
}
