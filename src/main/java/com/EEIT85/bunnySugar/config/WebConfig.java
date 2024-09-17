package com.EEIT85.bunnySugar.config;

import com.EEIT85.bunnySugar.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor; // 宣告 LoginInterceptor
    private final SecurityWhiteList securityWhiteList; // 引入 SecurityWhiteList 類別

    @Autowired
    public WebConfig(LoginInterceptor loginInterceptor, SecurityWhiteList securityWhiteList) {
        this.loginInterceptor = loginInterceptor;
        this.securityWhiteList = securityWhiteList;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 設定應用於所有路徑
                .allowedOrigins("http://localhost:5173", "http://localhost:5174", "http://127.0.0.1:5173", "http://127.0.0.1:5174", "http://127.0.0.1:8080") // 允許的前端 URL，根據實際情況修改
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允許的方法
                .allowedHeaders("*") // 允許的頭部信息
                .allowCredentials(true); // 是否允許憑證
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor) // 註冊 LoginInterceptor 攔截器
                .addPathPatterns("/api/**") // 指定需要攔截的路徑
                .excludePathPatterns(securityWhiteList.getWhitelistPaths().toArray(new String[0])); // 使用 SecurityWhiteList 提供的白名單路徑
    }
}
