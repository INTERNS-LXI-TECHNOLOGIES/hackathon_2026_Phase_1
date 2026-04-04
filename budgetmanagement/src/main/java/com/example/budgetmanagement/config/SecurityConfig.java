package com.example.budgetmanagement.config;

import com.example.budgetmanagement.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())

                // JWT is stateless — no session needed
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // Allow login, register, static files
                        .requestMatchers("/api/auth/login", "/api/registercontroller/registerpage",
                                         "/api/registercontroller/register", "/css/**", "/js/**", "/login.html").permitAll()

                        // Your existing RBAC — untouched ✅
                        .requestMatchers("/api/analytics/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/transaction/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/category/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/dashboard/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )

                // Add JwtFilter before Spring's default login filter
                .exceptionHandling(ex -> ex
    .authenticationEntryPoint((request, response, authException) -> {
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\"}");
    })
     .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(403);
                            response.setContentType("application/json");
                            response.getWriter().write(
                                "{\"status\": 403, \"error\": \"Forbidden - You don't have permission\"}"
                            );
                        })
                )
                
                
                
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Needed by AuthController to authenticate login
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}