package com.sunil.budget_tracker.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http  .csrf(csrf -> csrf.disable())
              .authorizeHttpRequests(auth -> auth
                // 1. MUST use .html here because it is a static file
                .requestMatchers("/login.html", "/register", "/css/**", "/js/**").permitAll()
                .requestMatchers("/controller/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated())
            
            .exceptionHandling(ex -> 
            ex.accessDeniedPage("/controller/accessDenied")


)


            .formLogin(form -> form
                // 2. Point exactly to the file in your static folder
                .loginPage("/login.html") 
                // 3. This is the POST destination for your HTML form
                .loginProcessingUrl("/login") 
                .usernameParameter("username")
                // 4. Your existing controller path
                .defaultSuccessUrl("/controller/budgetApp", true) 
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Allows plain text '1234' from MySQL to work
        return NoOpPasswordEncoder.getInstance();
    }
}


