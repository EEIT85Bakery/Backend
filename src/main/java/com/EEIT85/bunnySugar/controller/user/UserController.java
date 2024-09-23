package com.EEIT85.bunnySugar.controller.user;

import com.EEIT85.bunnySugar.dto.users.UsersDetailsDto;
import com.EEIT85.bunnySugar.dto.users.UsersLoginRequestDto;
import com.EEIT85.bunnySugar.dto.users.UsersVerifyDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.service.user.UserService;
import com.EEIT85.bunnySugar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registerVerify")
    public Map<String, Object> registerVerify(@RequestBody Users user) {
        Map<String, Object> response = new HashMap<>();

        Users registeredUser = userService.registerUserAndAll(user);
        if (registeredUser != null) {
            response.put("status", "success");
            response.put("message", "請檢查您的信箱以獲取驗證碼");
        } else {
            response.put("status", "error");
            response.put("message", "這個信箱已經註冊過BunnySugar囉！");
        }
        return response;
    }

    @PostMapping("/verify")
    public Map<String, Object> verifyUser(@RequestBody UsersVerifyDto userVerifyDto) {
        Map<String, Object> response = new HashMap<>();
        boolean isVerified = userService.verifyUser(userVerifyDto);

        if (isVerified) {
            response.put("status", "success");
            response.put("message", "驗證成功，請輸入您的會員資料！");
        } else {
            response.put("status", "error");
            response.put("message", "驗證失敗或驗證碼已過期！");
        }
        return response;
    }

    @PostMapping("/completeDetails")
    public Map<String, Object> completeDetails(@RequestBody UsersDetailsDto usersDetailsDto) {
        Map<String, Object> response = new HashMap<>();

        // 根據 email 獲取用戶
        Users user = userService.findByUserEmail(usersDetailsDto.getEmail());
        if (user == null) {
            response.put("status", "error");
            response.put("message", "用戶不存在");
            return response;
        }

        // 更新用戶詳細資料
        boolean updateSuccess = userService.updateUserDetails(usersDetailsDto.getEmail(), usersDetailsDto);
        if (updateSuccess) {
            response.put("status", "success");
            response.put("message", "註冊成功！歡迎光臨BunnySugar");
        } else {
            response.put("status", "error");
            response.put("message", "更新失敗，請確認用戶是否已驗證");
        }
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UsersLoginRequestDto loginRequest) {
        return userService.login(loginRequest);
    }
}