package com.pwr.mgp.config;

import com.pwr.mgp.enums.UserRole;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/registration", "/api/**", "/error").permitAll()
                        .requestMatchers("/settings")
                        .hasAuthority(UserRole.SUPERADMIN.name())
                        .requestMatchers("/private", "/private/**")
                        .hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name(), UserRole.SUPERADMIN.name())
                        .anyRequest().authenticated()
                )
                .build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
