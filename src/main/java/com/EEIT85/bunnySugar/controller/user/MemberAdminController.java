package com.EEIT85.bunnySugar.controller.user;

import com.EEIT85.bunnySugar.dto.users.MembeAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.MemberAdminDto;
import com.EEIT85.bunnySugar.service.user.MemberAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/members")
public class MemberAdminController {

    @Autowired
    private MemberAdminService memberService;

    // 查詢所有會員並分頁
    @GetMapping
    public ResponseEntity<Page<MemberAdminDto>> getAllMembers(
            @RequestParam(defaultValue = "1") int page,   // 當前的頁碼，預設為第1頁（索引從0開始）
            @RequestParam(defaultValue = "10") int size   // 每頁顯示的資料數量，默認為10條
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MemberAdminDto> membersPage = memberService.getAllMembers(pageable);
        return ResponseEntity.ok(membersPage);
    }

    // 根據ID查詢會員
    @GetMapping("/{id}")
    public ResponseEntity<MemberAdminDto> getMemberById(@PathVariable UUID id) {
        MemberAdminDto member = memberService.getMemberById(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 更新會員資料
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(
            @PathVariable UUID id, @RequestBody MembeAdminUpdateDto updatedMemberDto) {
        boolean success = memberService.updateMember(id, updatedMemberDto);
        if (success) {
            return ResponseEntity.ok("會員資料更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員不存在");
        }
    }
}

