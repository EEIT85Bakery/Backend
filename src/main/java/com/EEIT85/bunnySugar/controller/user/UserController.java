package com.EEIT85.bunnySugar.controller.user;

import com.EEIT85.bunnySugar.dto.users.UsersDetailsDto;
import com.EEIT85.bunnySugar.dto.users.UsersLoginRequestDto;
import com.EEIT85.bunnySugar.dto.users.UsersSetPasswordDto;
import com.EEIT85.bunnySugar.dto.users.UsersVerifyDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.service.user.UserService;
import com.EEIT85.bunnySugar.utils.JwtUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, Object>> registerVerify(@RequestBody Users user) throws MessagingException {
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<?> registeredUserResponse = userService.registerUserAndAll(user);

        // 檢查返回的 ResponseEntity
        if (registeredUserResponse.getStatusCode() == HttpStatus.OK) {
            response.put("status", "success");
            response.put("message", "請檢查您的信箱以獲取驗證碼");
        } else {
            response.put("status", "error");
            response.put("message", registeredUserResponse.getBody() != null ? registeredUserResponse.getBody() : "這個信箱已經註冊過BunnySugar囉！");
        }

        return ResponseEntity.ok(response); // 返回整個響應
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyUser(@RequestBody UsersVerifyDto userVerifyDto) {
        Map<String, Object> response = new HashMap<>();
        boolean isVerified = userService.verifyUser(userVerifyDto);

        if (isVerified) {
            response.put("status", "success");
            response.put("message", "驗證成功，請輸入您的會員資料！");
            return ResponseEntity.ok(response);  // 驗證成功，返回 200 OK
        } else {
            response.put("status", "error");
            response.put("message", "驗證失敗或驗證碼已過期！");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // 驗證失敗，返回 400 Bad Request
        }
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
    public ResponseEntity<Map<String, Object>> login(@RequestBody UsersLoginRequestDto loginRequest) {
        Map<String, Object> response = userService.login(loginRequest);

        // 根據 response 中的 status 決定要返回的 HTTP 狀態碼
        if ("error".equals(response.get("status"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // 狀態錯誤時，回傳 401
        }

        return ResponseEntity.ok(response); // 成功時，回傳 200
    }

    //google登入的方法
    @PostMapping("/googleLogin")
    public ResponseEntity<Map<String, Object>> googleLogin(@RequestBody Map<String, String> request) {
        String googleToken = request.get("googleToken");
        Map<String, Object> response = userService.verifyGoogleToken(googleToken);
//        System.out.println("有成功進來處理google登入用戶詳情");

        if ("error".equals(response.get("status"))) {
            System.out.println("進入有錯誤回應");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // 狀態錯誤時，回傳 401
        }

        return ResponseEntity.ok(response); // 成功時，回傳 200
    }

    @PostMapping("/sentResetPasswordEmail")
    public Map<String, Object> sentResetPasswordEmail(@RequestBody Users user) throws MessagingException {
        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> result = userService.sentResetPasswordEmail(user);

        if (result.getStatusCode() == HttpStatus.OK) {
            response.put("status", "success");
            response.put("message", result.getBody());
        } else {
            response.put("status", "error");
            response.put("message", result.getBody());
        }
        return response;
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody UsersSetPasswordDto usersSetPasswordDto) throws MessagingException {
        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> result = userService.resetPassword(usersSetPasswordDto);

        if (result.getStatusCode() == HttpStatus.OK) {
            response.put("status", "success");
            response.put("message", result.getBody());
            return ResponseEntity.ok(response); // 直接返回封裝好的 response
        } else {
            response.put("status", "error");
            response.put("message", result.getBody());
            return ResponseEntity.badRequest().body(response); // 返回 Bad Request 狀態
        }
    }


}
