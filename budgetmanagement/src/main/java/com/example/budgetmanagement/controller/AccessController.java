package com.example.budgetmanagement.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/controller")
public class AccessController {

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
