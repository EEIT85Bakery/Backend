package com.EEIT85.bunnySugar.controller.user;

import com.EEIT85.bunnySugar.dto.users.front.MemberFrontUpdateDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.UserRepository;
import com.EEIT85.bunnySugar.service.user.UserService;
import com.EEIT85.bunnySugar.service.user.front.MemberFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/memberPage")
public class MemberController {

    @Autowired
    private MemberFrontService memberFrontService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/memberData")
    public Map<String, Object> list() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("message", "JWT驗證成功，會員資料...");
        return res;
    }

    // 查詢會員完整資料
    @PostMapping("/{id}")
    public ResponseEntity<MemberFrontUpdateDto> getMemberById(@PathVariable Long id) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            // 將會員資料封裝到 DTO
            MemberFrontUpdateDto memberFrontUpdateDto = new MemberFrontUpdateDto();
            memberFrontUpdateDto.setName(user.getName());
            memberFrontUpdateDto.setGender(user.getGender());
            memberFrontUpdateDto.setPhone(user.getPhone());
            memberFrontUpdateDto.setBirthday(user.getBirthday());
            memberFrontUpdateDto.setEmail(user.getEmail());
            memberFrontUpdateDto.setGameTimes(user.getGameTimes());
            memberFrontUpdateDto.setBunnyCoin(user.getBunnyCoin());

            return ResponseEntity.ok(memberFrontUpdateDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    // 更新會員資料
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(
            @PathVariable Long id, @RequestBody MemberFrontUpdateDto updatedMemberDto) {
        boolean success = memberFrontService.updateMember(id, updatedMemberDto);
        if (success) {
            return ResponseEntity.ok("會員資料更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員不存在");
        }
    }
}
