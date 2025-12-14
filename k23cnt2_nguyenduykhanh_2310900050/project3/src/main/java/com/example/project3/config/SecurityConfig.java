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
                .csrf(csrf -> csrf.disable()) // Tắt CSRF để test dễ dàng hơn
                .authorizeHttpRequests((requests) -> requests
                        // 1. ADMIN: Yêu cầu quyền ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // 2. CÔNG KHAI (Public): Ai cũng vào được
                        .requestMatchers("/", "/product/**", "/login", "/register", "/logout", "/css/**", "/images/**", "/js/**").permitAll()

                        // 3. CÁC TRANG CÒN LẠI (Giỏ hàng, Thanh toán, Lịch sử): Bắt buộc đăng nhập
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login") // Trang login tùy chỉnh
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                                // Phân luồng sau khi đăng nhập thành công
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