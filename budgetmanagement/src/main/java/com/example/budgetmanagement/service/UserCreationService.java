package com.example.budgetmanagement.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.budgetmanagement.model.*;
import com.example.budgetmanagement.repository.*;

@Service
public class UserCreationService {

    @Autowired
    private UserCreationRepository userCreationRepository;

    @Autowired
    private RoleRepository roleRepository;

     @Autowired
     private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password) {

        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));

        Role role = roleRepository.findByRoleName("ROLE_USER");

        user.setRoles(Set.of(role));
        userCreationRepository.save(user);
    }
}