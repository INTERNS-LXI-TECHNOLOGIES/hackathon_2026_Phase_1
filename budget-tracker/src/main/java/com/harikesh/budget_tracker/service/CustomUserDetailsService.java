// package com.harikesh.budget_tracker.service;

// import org.springframework.security.core.userdetails.*;
// import org.springframework.stereotype.Service;
// import com.harikesh.budget_tracker.entity.User;
// import com.harikesh.budget_tracker.repository.UserRepository;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     private final UserRepository repo;

//     public CustomUserDetailsService(UserRepository repo) {
//         this.repo = repo;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = repo.findByUsername(username);

//         if (user == null) {
//             throw new UsernameNotFoundException("User not found");
//         }

//         return org.springframework.security.core.userdetails.User
//                 .withUsername(user.getUsername())
//                 .password(user.getPassword())
//                 .roles("USER")
//                 .build();
//     }
// }