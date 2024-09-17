package com.EEIT85.bunnySugar.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;
import com.EEIT85.bunnySugar.utils.JwtUtil;

@Component
@Slf4j
public class JwtSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // 檢查請求是否是 WebSocket 端點
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/ws")) {
            // 為 WebSocket 請求創建一個空的身份驗證
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(null, null, List.of())
            );
            // 直接通過，不進行 JWT 驗證
            filterChain.doFilter(request, response);
            return;
        }


        // 提取 JWT
        System.out.println("有到jwtFilter");
        String token = extractToken(request);
        if (token != null && jwtUtil.isTokenValid(token)) {
            Map<String, Object> claims = jwtUtil.parseJwtToken(token);
            if (claims != null) {
                // 根據 claims 設置用戶認證資訊
                String account = (String) claims.get("account");
                String role = (String) claims.get("role");

                // 使用 role 來設置權限
                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(account, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // 將 claims 存入 request 屬性中
                request.setAttribute("claims", claims);
            } else {
                SecurityContextHolder.clearContext();
            }
        } else {
            SecurityContextHolder.clearContext();
        }

        // 繼續執行後續的過濾鏈
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7); // 移除 "Bearer " 前綴
        }
        return null;
    }
}
