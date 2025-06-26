package com.example.mincheol1st.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (테스트용)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/signup", "/api/login", "/api/posts/**", "/api/comments/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html").permitAll() // 이 경로는 인증 없이 허용
                        .anyRequest().authenticated() // 나머지는 인증 필요
                );

        return http.build();
    }
}
