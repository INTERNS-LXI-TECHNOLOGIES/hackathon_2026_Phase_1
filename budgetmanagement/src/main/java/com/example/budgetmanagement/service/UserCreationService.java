package com.example.budgetmanagement.service;

import com.example.budgetmanagement.model.Role;
import com.example.budgetmanagement.model.User;
import com.example.budgetmanagement.repository.RoleRepositry;
import com.example.budgetmanagement.repository.UserRepositry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserCreationService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepositry userRepository;

    @Autowired
    private RoleRepositry roleRepository;

    public void registerUser(String username, String password) {

        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        
        Role userRole = roleRepository.findByRoleName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        
        userRepository.save(user);
}
}