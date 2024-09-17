package com.EEIT85.bunnySugar.service.user;

import com.EEIT85.bunnySugar.dto.users.UsersDetailsDto;
import com.EEIT85.bunnySugar.dto.users.UsersLoginRequestDto;
import com.EEIT85.bunnySugar.dto.users.UsersSetPasswordDto;
import com.EEIT85.bunnySugar.dto.users.UsersVerifyDto;
import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.entity.WishList;
import com.EEIT85.bunnySugar.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.EEIT85.bunnySugar.utils.JwtUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersCartService usersCartService;

    @Autowired
    private UsersWishListService usersWishListService;

    @Autowired
    private VerificationEmailService verificationEmailService;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<?> registerUserAndAll(Users user) throws MessagingException {
        // 檢查信箱是否已存在
        Users existingUser = findByUserEmail(user.getEmail());

        if (existingUser != null) {
            if (existingUser.getDetailsCompleted() == 1) {
                return ResponseEntity.badRequest().body("此信箱已經註冊過囉");
            } else {
                existingUser.setActive(0);
                existingUser.setAccumulateSpent(0);
                existingUser.setUserVip("白兔");
                existingUser.setUpdateTime(LocalDateTime.now());
                user.setUserRole("ADMIN");
                String verifyingToken = String.format("%06d", (int)(Math.random() * 1000000));
                existingUser.setVerifyingToken(verifyingToken);
                existingUser.setTokenExpirationTime(LocalDateTime.now().plusMinutes(10));

                // 使用新的 VerificationEmailService 發送驗證信
                verificationEmailService.sendVerificationEmail(existingUser.getEmail(), verifyingToken);

                userRepository.save(existingUser);
                return ResponseEntity.ok(existingUser);
            }
        }

        // 新用戶註冊
        user.setActive(0);  // 未驗證
        user.setAccumulateSpent(0);
        user.setUserVip("白兔");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setUserRole("ADMIN");
        String verifyingToken = String.format("%06d", (int)(Math.random() * 1000000));
        user.setVerifyingToken(verifyingToken);
        user.setTokenExpirationTime(LocalDateTime.now().plusMinutes(10));

        user = userRepository.save(user);

        usersCartService.createCartForUser(user);
        usersWishListService.createWishListForUser(user);

        // 發送驗證信
        verificationEmailService.sendVerificationEmail(user.getEmail(), verifyingToken);

        return ResponseEntity.ok(user); // 返回保存的使用者物件
    }

    public boolean verifyUser(UsersVerifyDto userVerifyDto) {
        // 從 Dto 中獲取 email 和驗證碼
        String email = userVerifyDto.getEmail();
        String token = userVerifyDto.getVerifyingToken();

        Users user = userRepository.findByEmail(email);
        if (user == null) {
            return false; // 用戶不存在，return false
        }
        if (!user.getVerifyingToken().equals(token)) {
            return false; // 驗證碼不正確，return false
        }
        if (user.getTokenExpirationTime().isBefore(LocalDateTime.now())) {
            return false; // 驗證碼已過期，return false
        }

        // 驗證成功，更新用戶狀態
        user.setActive(1); // 更新為已驗證
        userRepository.save(user); // 保存用戶狀態

        return true; // 驗證成功
    }
    //////////////重要，這邊因為我是用email去找，但這個頁面不會讓用戶輸入email了，所以前端要用vuex儲存email放到下一個請求
    public boolean updateUserDetails(String email, UsersDetailsDto usersDetailsDto) {
        // 根據 email 找出那個用戶的資料
        Users user = userRepository.findByEmail(email);

        // 確認用戶是否存在，也去確認active是否為1
        if (user == null || user.getActive() != 1) {
            return false; // 用戶不存在或未驗證
        }

        //這邊做了一個防止前端有人改資料，讓我原本拿的到的資料拿不到，但卻還是可以新增進去，這樣基本都可以有資料
        if (usersDetailsDto.getAccount() != null) {
            user.setAccount(usersDetailsDto.getAccount());
        }
        if (usersDetailsDto.getPassword() != null) {
            // 將密碼轉換為 Bcrypt
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(usersDetailsDto.getPassword());
            user.setPassword(encodedPassword);
        }
        if (usersDetailsDto.getName() != null) {
            user.setName(usersDetailsDto.getName());
        }
        if (usersDetailsDto.getGender() != null) {
            user.setGender(usersDetailsDto.getGender());
        }
        if (usersDetailsDto.getBirthday() != null) {
            user.setBirthday(usersDetailsDto.getBirthday());
        }
        user.setBunnyCoin(50);
        user.setGameTimes(0);
        user.setDetailsCompleted(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now()); // 更新時間設為當前時間

        // 更新購物車物件的 createTime 和 updateTime
        Cart cart = user.getCart();
        if (cart != null) {
            cart.setCreateTime(user.getUpdateTime());  // 使用用戶的更新時間作為購物車的創建時間
            cart.setUpdateTime(user.getUpdateTime());  // 同理
        }

        // 更新願望清單物件的 createTime 和 updateTime
        WishList wishList = user.getWishList();
        if (wishList != null) {
            wishList.setCreateTime(user.getUpdateTime());  // 使用用戶的更新時間作為願望清單的創建時間
            wishList.setUpdateTime(user.getUpdateTime());  // 同理
        }
        // 儲存更新後的用戶資料與關聯物件
        userRepository.save(user);
        return true; // 資料更新成功
    }

    public Map<String, Object> login(UsersLoginRequestDto loginRequest) {
        Map<String, Object> response = new HashMap<>();

        // 根據帳號查找用戶
        Users loginedUser = findByUserAccount(loginRequest.getAccount());
        if (loginedUser == null) {
            response.put("status", "error");
            response.put("message", "帳號不存在");
            return response; // 不要生成 JWT，直接返回
        }

        // 驗證密碼
        if (!BCrypt.checkpw(loginRequest.getPassword(), loginedUser.getPassword())) {
            response.put("status", "error");
            response.put("message", "密碼錯誤");
            return response; // 不要生成 JWT，直接返回
        }

        // 更新最後登錄時間
        LocalDateTime now = LocalDateTime.now();
        userRepository.updateLastLoginTime(loginedUser.getId(), now);

        // 檢查用戶身分
        String role = loginedUser.getUserRole();
        String account = loginedUser.getAccount();
        // 生成 JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginedUser.getId());
        claims.put("account", loginedUser.getAccount());
        claims.put("user", role); // 將身分添加到 claims 中
        System.out.println(claims);
        String token = jwtUtil.generateToken(claims);

        // 檢查 JWT token 是否能解析成功
        try {
            Map<String, Object> parsedClaims = jwtUtil.parseJwtToken(token);
            System.out.println("Parsed Claims: " + parsedClaims);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Token 解析失敗: " + e.getMessage());
            return response;
        }

        response.put("status", "success");
        response.put("account", account);
        response.put("token", token);
        response.put("user", role);
        return response;
    }

    public Map<String, Object> verifyGoogleToken(String googleToken) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 使用 Firebase 驗證 Google ID Token
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(googleToken);

            String googleId = decodedToken.getUid(); // user_id
            String email = decodedToken.getEmail();
            String name = decodedToken.getName();

            // 如果 email 為空，則驗證失敗
            if (email == null || email.isEmpty()) {
                response.put("status", "error");
                response.put("message", "Google ID Token 中沒有包含 email 資訊");
                return response;
            }

            // 查詢用戶是否存在
            Users user = userRepository.findByEmail(email);
            if (user == null) {
                // 創建新用戶
                user = new Users();
                user.setEmail(email);
                user.setName(name);
                user.setAccount(email);
                user.setAccumulateSpent(0);
                user.setBunnyCoin(50);
                user.setUserVip("白兔");
                user.setCreateTime(LocalDateTime.now());
                user.setUpdateTime(LocalDateTime.now());
                user.setLastLoginTime(LocalDateTime.now());
                user.setActive(1);  // 設置用戶狀態為已驗證
                user.setDetailsCompleted(1);
                user.setGoogleToken(googleId); // 將 user_id 存入 googleId 字段
                user.setUserRole("ADMIN");
                user = userRepository.save(user);  // 保存新用戶到資料庫
                usersWishListService.createWishListForUser(user);
                usersCartService.createCartForUser(user);
            } else {
                // 如果用戶已存在，檢查 googleToken 是否為空
                if (user.getGoogleToken() == null || user.getGoogleToken().isEmpty()) {
                    user.setGoogleToken(googleId); // 只在 googleToken 為空時更新
                }
                user.setLastLoginTime(LocalDateTime.now()); // 始終更新最後登入時間
                userRepository.save(user);
            }

            String account = user.getAccount();
            // 使用用戶的 ID 來生成 JWT
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("account", user.getAccount());  // 使用 email 作為 account

            // 根據用戶的 ID 判斷身分
            String role = user.getUserRole();
            claims.put("role", role); // 將身分添加到 claims 中

            String token = jwtUtil.generateToken(claims);

            response.put("status", "success");
            response.put("account", account);
            response.put("token", token);
            response.put("user", role);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Google ID Token 驗證失敗: " + e.getMessage());
            return response;
        }
    }

    public ResponseEntity<?> sentResetPasswordEmail(Users user) throws MessagingException {
        Users existingUser = findByUserEmail(user.getEmail());
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("用戶不存在"); // 如果用戶不存在，返回錯誤訊息
        }

        // 生成新的驗證碼
        String newToken = String.format("%06d", (int) (Math.random() * 1000000));
        existingUser.setVerifyingToken(newToken); // 更新用戶的驗證碼
        existingUser.setTokenExpirationTime(LocalDateTime.now().plusMinutes(10)); // 設置過期時間

        userRepository.save(existingUser);
        verificationEmailService.sendVerificationEmail(existingUser.getEmail(), newToken); // 寄送驗證信

        return ResponseEntity.ok("已發送新的驗證碼到您的電子郵件，請確認後再重設密碼");
    }

    public ResponseEntity<?> resetPassword(UsersSetPasswordDto usersSetPasswordDto) throws MessagingException {
        String email = usersSetPasswordDto.getEmail();
        String newPassword = usersSetPasswordDto.getPassword();

        Users user = findByUserEmail(email);
        // 密碼加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword); // 更新用戶密碼

        //刪掉用戶資料中的驗證碼
        user.setVerifyingToken(null);
        user.setTokenExpirationTime(null);

        userRepository.save(user);

        return ResponseEntity.ok("密碼重設成功"); // 返回成功訊息
    }




    // Find user by account
    public Users findByUserAccount(String account) {
        return userRepository.findByAccount(account); // Ensure this method is present in UserRepository
    }

    public Users findByUserEmail(String email) {
        return userRepository.findByEmail(email); // 確保這個方法在 UserRepository 中存在
    }
}
