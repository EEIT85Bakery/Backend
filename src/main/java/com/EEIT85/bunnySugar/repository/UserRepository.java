package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminDto;
import com.EEIT85.bunnySugar.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByAccount(String account);
    Users findByVerifyingToken(String token);
    Users findByEmail(String email);  // 根據信箱查詢用戶

    // 查詢所有會員的DTO並分頁
    @Query("SELECT NEW com.EEIT85.bunnySugar.dto.users.admin.MemberAdminDto(" +
            "u.name, u.gender, u.email, u.phone, u.birthday, u.bunnyCoin, " +
            "u.gameTimes) FROM Users u")
    Page<MemberAdminDto> findAllMemberAdminSelectDto(Pageable pageable);

    // 根據ID查詢會員DTO
    @Query("SELECT NEW com.EEIT85.bunnySugar.dto.users.admin.MemberAdminDto(" +
            "u.name, u.gender, u.email, u.phone, u.birthday, u.bunnyCoin, " +
            "u.gameTimes) FROM Users u WHERE u.id = :id")
    MemberAdminDto findMemberAdminSelectDtoById(@Param("id") Long id);
}

