package com.EEIT85.bunnySugar.controller.user;

import com.EEIT85.bunnySugar.dto.users.front.MemberFrontDto;
import com.EEIT85.bunnySugar.dto.users.front.MemberFrontUpdateDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.UserRepository;
import com.EEIT85.bunnySugar.service.user.UserService;
import com.EEIT85.bunnySugar.service.user.front.MemberFrontService;
import jakarta.servlet.http.HttpServletRequest;
import org.springdoc.core.converters.ResponseSupportConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/memberPage")
public class MemberController {

    @Autowired
    private MemberFrontService memberFrontService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResponseSupportConverter responseSupportConverter;

    @GetMapping("/memberData")
    public Map<String, Object> list() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("message", "JWT驗證成功，會員資料...");
        return res;
    }

//    // 查詢會員資料
//    @GetMapping("/{id}")
//    public ResponseEntity<MemberFrontDto> getMemberById(@PathVariable Long id) {
//        MemberFrontDto memberFrontDto = memberFrontService.getMemberById(id);
//        return ResponseEntity.ok(memberFrontDto);
//    }

    // 根據會員ID查詢會員
    @GetMapping("/getById")
    public Object getMemberById(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        System.out.println(userId);
        if (userId == null) {
            throw new IllegalArgumentException("User ID is null");
        }
        Optional<Users> member = memberFrontService.getMemberById(userId);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMember(HttpServletRequest request, @RequestBody MemberFrontUpdateDto updatedMemberDto) {
        Long userId = (Long) request.getAttribute("userId");
        System.out.println("Updating User ID: " + userId);

        if (userId == null) {
            throw new IllegalArgumentException("User ID is null");
        }

        boolean isUpdated = memberFrontService.updateMember(userId, updatedMemberDto);
        if (isUpdated) {
            return ResponseEntity.ok("會員資料更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員未找到，更新失敗");
        }
    }

}
