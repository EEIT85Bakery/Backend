package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    Users findByAccount(String account);
    Users findByEmail(String email);  // 根據信箱查詢用戶
    Page<Users> findAll(Pageable pageable);  // 自動支持分頁查詢

}

