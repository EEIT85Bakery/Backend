package com.EEIT85.bunnySugar.controller.user.admin;

import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminDto;
import com.EEIT85.bunnySugar.service.user.admin.MemberAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/members")
public class MemberAdminController {

    @Autowired
    private MemberAdminService memberAdminService;

    // 查詢所有會員並分頁
    @PostMapping
    public ResponseEntity<Page<MemberAdminDto>> getAllMembers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MemberAdminDto> membersPage = memberAdminService.getAllMembers(pageable);
        return ResponseEntity.ok(membersPage);
    }

    // 根據ID查詢會員
    @GetMapping("/{id}")
    public ResponseEntity<MemberAdminDto> getMemberById(@PathVariable Long id) {
        MemberAdminDto member = memberAdminService.getMemberById(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 更新會員的 userVip 屬性
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMemberVip(
            @PathVariable Long id, @RequestBody MemberAdminUpdateDto updatedMemberDto) {
        boolean success = memberAdminService.updateMemberVip(id, updatedMemberDto);
        if (success) {
            return ResponseEntity.ok("會員 VIP 資料更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員不存在");
        }
    }
}

