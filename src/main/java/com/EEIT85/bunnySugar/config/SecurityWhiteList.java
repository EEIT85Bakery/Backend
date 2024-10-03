package com.EEIT85.bunnySugar.config;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityWhiteList {

    // 定義白名單路徑，擴展靜態資源路徑
    private static final List<String> WHITELIST_PATHS = List.of(
            "/user/login",
            "/user/registerVerify",
            "/user/verify",
            "/user/completeDetails",
            "/user/googleLogin",
            // 靜態資源路徑白名單
            "/test.html",
            "/static/**",  // 配置靜態資源路徑
            "/",           // 允許根目錄
            "/index.html",  // 主頁面
            "/favicon.ico", // 網站圖標
            "/js/**",       // 允許 JavaScript 資源
            "/css/**",      // 允許 CSS 資源
            "/images/**"    // 允許圖片資源
    );

    // 提供方法來返回白名單路徑（作為 List）
    public List<String> getWhitelistPaths() {
        return WHITELIST_PATHS;
    }

    // 提供方法來返回白名單路徑（作為 String[]）
    public String[] getWhitelistPathsAsArray() {
        return WHITELIST_PATHS.toArray(new String[0]);
    }
}
