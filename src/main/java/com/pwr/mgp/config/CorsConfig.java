package com.pwr.mgp.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfiguration() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**") // разрешить для всех путей
                        .allowedOrigins("*") // разрешить любые источники
                        .allowedMethods("*") // разрешить все HTTP-методы: GET, POST, PUT, DELETE и т.д.
                        .allowedHeaders("*") // разрешить любые заголовки
                        .allowCredentials(false); // если не нужны куки и авторизация
            }
        };
    }
}