package com.EEIT85.bunnySugar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtSecurityFilter jwtSecurityFilter;

    @Autowired
    private SecurityWhiteList securityWhiteList; // 注入 SecurityWhitelist 類別

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // 使用 WebConfig 中的 CORS 設定
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/sentResetPasswordEmail").permitAll()
                        .requestMatchers(securityWhiteList.getWhitelistPathsAsArray()).permitAll() // 使用 SecurityWhitelist 中的路徑
                        .anyRequest().authenticated() // 其他請求需要認證
                )
                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class); // 添加 JWT 过滤器

        return http.build();
    }
}
