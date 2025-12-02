package com.example.project3.config;

import com.example.project3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        // 1. ADMIN: Chỉ Admin mới vào được
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // 2. CÔNG KHAI: Chỉ cho phép Trang chủ, Login, Register, Ảnh, CSS
                        .requestMatchers("/", "/login", "/register", "/logout", "/css/**", "/images/**", "/js/**").permitAll()

                        // 3. CÁC TRANG CÒN LẠI (Xem chi tiết, Giỏ hàng)
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        // Logic chuyển hướng sau khi đăng nhập thành công
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                                if (roles.contains("ROLE_ADMIN")) {
                                    response.sendRedirect("/admin");
                                } else {
                                    response.sendRedirect("/");
                                }
                            }
                        })
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}