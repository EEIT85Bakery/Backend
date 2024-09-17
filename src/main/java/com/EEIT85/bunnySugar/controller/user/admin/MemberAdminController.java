package com.EEIT85.bunnySugar.controller.user.admin;

import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.service.user.admin.MemberAdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/members")
public class MemberAdminController {

    @Autowired
    private MemberAdminService memberAdminService;

    // 查詢所有會員並分頁，支持電話搜尋
    @GetMapping
    public ResponseEntity<Page<MemberAdminDto>> getAllMembers(
            HttpServletRequest request,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String userPhone
    ) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MemberAdminDto> membersPage;

        if (userPhone != null && !userPhone.isEmpty()) {
            membersPage = memberAdminService.getMembersByUserPhone(userPhone, pageable);
        } else {
            membersPage = memberAdminService.getAllMembers(pageable);
        }

        return ResponseEntity.ok(membersPage);
    }
    // 根據會員ID查詢會員
    @GetMapping("/getById")
    public Object getMemberById(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        System.out.println(userId);
        if (userId == null) {
            throw new IllegalArgumentException("User ID is null");
        }
        Optional<Users> member = memberAdminService.getMemberById(userId);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 更新會員的 userVip 屬性
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMemberVip(
            HttpServletRequest request,  // 確保用戶已登入
            @PathVariable Long id,
            @RequestBody MemberAdminUpdateDto updatedMemberDto) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("未授權的操作");
        }

        boolean success = memberAdminService.updateMemberVip(id, updatedMemberDto);
        if (success) {
            return ResponseEntity.ok("會員 VIP 資料更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員不存在");
        }
    }

    // 刪除會員
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemberById(
            HttpServletRequest request,  // 確保用戶已登入
            @PathVariable Long id) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("未授權的操作");
        }

        boolean success = memberAdminService.deleteMemberById(id);
        if (success) {
            return ResponseEntity.ok("會員刪除成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員不存在");
        }
    }
}

