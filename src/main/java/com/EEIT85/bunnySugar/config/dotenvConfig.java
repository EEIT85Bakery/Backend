package com.EEIT85.bunnySugar.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//spring配置類別，用來載入.env的東西
@Configuration
public class dotenvConfig {

    @Bean   //這邊載入.env的敏感資訊
    public Dotenv dotenv() {
        return Dotenv.load();
    }
}
