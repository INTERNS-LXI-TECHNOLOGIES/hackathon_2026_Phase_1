package com.example.budgetmanagement.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.budgetmanagement.model.*;
import com.example.budgetmanagement.repository.*;

@Service
public class UserCreationService {

    @Autowired
    private UserCreationRepository userCreationRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void registerUser(String username, String password) {

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        Role role = roleRepository.findByRoleName("ROLE_USER");

        user.setRoles(Set.of(role));

        userCreationRepository.save(user);
    }
}