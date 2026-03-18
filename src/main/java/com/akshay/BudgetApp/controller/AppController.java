package com.akshay.BudgetApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/budgetApp")
public class AppController {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(String username, String password) {

        if(username.equals("admin") && password.equals("1234")) {
            return "Menu";
        }

        return "login";
    }
     

    @GetMapping("/addTransaction")
    public String addTransactionPage() {
        return "AddTransaction";
    }

    @GetMapping("/addCategory")
    public String addCategoryPage() {
        return "AddCategory";
    }

    @GetMapping("/listAmount")
    public String listAmountPage() {
        return "ListAmount";
    }

    @GetMapping("/listDate")
    public String listDatePage() {
        return "ListDate";
    }

    @GetMapping("/status")
    public String statusPage() {
        return "Status";
    }

    @GetMapping("/exit")
    public String exitPage() {
        return "Exit";
    }
}