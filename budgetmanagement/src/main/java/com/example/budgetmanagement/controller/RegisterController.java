
package com.example.budgetmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.budgetmanagement.dto.AuthRequest;
import com.example.budgetmanagement.model.*;
import com.example.budgetmanagement.service.*;

@RestController
@RequestMapping("/api/registercontroller")
public class RegisterController {

    @Autowired
    private UserCreationService userCreationService;

    @GetMapping("/registerpage")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    
    public String registerUser(@RequestBody AuthRequest request)
             {

        userCreationService.registerUser(request.getUsername(),request.getPassword());

        return "user registered successfully!";
    }
}