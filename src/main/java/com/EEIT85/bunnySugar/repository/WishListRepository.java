package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.wishList.WishListSelectDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.entity.WishList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    // 查詢某用戶的收藏清單
    Page<WishList> findByUsersId(Long userId, Pageable pageable);

    Optional<WishList> findByUsersId(Long userId);

}
