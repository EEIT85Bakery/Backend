package com.EEIT85.bunnySugar.service.user;

import com.EEIT85.bunnySugar.dto.users.UsersDetailsDto;
import com.EEIT85.bunnySugar.dto.users.UsersLoginRequestDto;
import com.EEIT85.bunnySugar.dto.users.UsersVerifyDto;
import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.entity.WishList;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.EEIT85.bunnySugar.utils.JwtUtil;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private WishListService wishListService;
    @Autowired
    private JwtUtil jwtUtil;

    public Users registerUserAndAll(Users user) {
        // 檢查信箱是否已存在
        Users existingUser = findByUserEmail(user.getEmail());

        if (existingUser != null) {
            if (existingUser.getDetailsCompleted() == 1) {
                return null; // 信箱存在且資料已完善，返回 null
            } else {
                // 更新未完善資料的用戶
                existingUser.setActive(0); // 未驗證
                existingUser.setUpdateTime(LocalDateTime.now());
                String verifyingToken = String.format("%06d", (int)(Math.random() * 1000000));
                existingUser.setVerifyingToken(verifyingToken);
                existingUser.setTokenExpirationTime(LocalDateTime.now().plusMinutes(10));
                // 更新資料
                userRepository.save(existingUser);
                return user; // 返回保存的使用者物件
            }
        }

        user.setActive(0); // 未驗證
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        String verifyingToken = String.format("%06d", (int)(Math.random() * 1000000));
        user.setVerifyingToken(verifyingToken);
        user.setTokenExpirationTime(LocalDateTime.now().plusMinutes(10));

        user = userRepository.save(user);

        // 創建購物車
        cartService.createCartForUser(user);

        // 創建願望清單
        wishListService.createWishListForUser(user);

        return user; // 返回保存的使用者物件
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
        user.setBunnyCoin(0);
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
            return response;
        }

        // 驗證密碼
        if (!BCrypt.checkpw(loginRequest.getPassword(), loginedUser.getPassword())) {
            response.put("status", "error");
            response.put("message", "密碼錯誤");
            return response;
        }

        // 生成 JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginedUser.getId());
        claims.put("account", loginedUser.getAccount());
        Date expirationTime = new Date(System.currentTimeMillis() + 30 * 24 * 3600 * 1000); // 維持一個月
        String token = jwtUtil.generateToken(claims, expirationTime);

        response.put("status", "success");
        response.put("token", token);
        return response;
    }


    // Find user by account
    public Users findByUserAccount(String account) {
        return userRepository.findByAccount(account);
    }

    public Users findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
