package com.EEIT85.bunnySugar.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.EEIT85.bunnySugar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Autowired
    public LoginInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // LoginInterceptor 只用來處理已通過 JwtSecurityFilter 的請求
        System.out.println("LoginInterceptor: preHandle executed");

        // 從 Request 中獲取 JWT 解析的 claims
        Map<String, Object> claims = (Map<String, Object>) request.getAttribute("claims");
        if (claims != null) {
            // 取得 JWT 中的 userId 和 account
            Long userId = null;
            Object idClaim = claims.get("id");

            // 確保 idClaim 不是 null 並且進行轉換
            if (idClaim instanceof Number) {
                userId = ((Number) idClaim).longValue(); // 將其轉換為 Long
            } else if (idClaim instanceof String) {
                userId = Long.parseLong((String) idClaim); // 如果 idClaim 是 String，轉換為 Long
            } else {
                System.out.println("ID claim is not a valid type.");
            }

            String account = claims.get("account").toString();

            // 印出 userId 和 account 的類型
//            System.out.println("userId type: " + ((Object) userId).getClass().getName());
//            System.out.println("account type: " + ((Object) account).getClass().getName());

            // 設置到 request 的 Attributes 中
            request.setAttribute("userId", userId);
            request.setAttribute("account", account);

//            System.out.println("LoginInterceptor: claims set | userId: " + userId + ", account: " + account);
            return true; // 放行，繼續處理請求
        }

        // 如果 claims 為 null，代表 JwtSecurityFilter 沒有設置或解析失敗，則直接return401未授權
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Unauthorized: Invalid or missing claims");
        return false;
    }

}
