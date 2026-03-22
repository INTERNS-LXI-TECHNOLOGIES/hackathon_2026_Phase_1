package com.akshay.BudgetApp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/budgetApp")
public class AppController {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "Login";
    }

    @PostMapping("/login"   )
    public String login(String username, String password) {

        if(username.equals("admin") && password.equals("1234")) {
            return "Menu";
        }

        return "login";
    }


    @GetMapping("/addTransactionPage")
    public String addTransactionPage () {
        return "AddTransaction";
    }
     

    @PostMapping("/addTransaction")
    public String addTransactionPage( @RequestParam String description ,@RequestParam double amount ,@RequestParam String categoryName ,@RequestParam String type,
        @RequestParam String date)
        
        {

            System.out.print(description + " " + amount);
        return "Menu";
    }


     @GetMapping("/addCategoryPage")
    public String addCategoryPage() {
        return "AddCategory";
    }

  

    @PostMapping ("/addCategory") 

    public String saveCategory (@RequestParam String CategoryName) {
   
        System.out.print(CategoryName);
        return "Menu";

    }


   
@GetMapping("/listAmount")
public String listAmountPage(Model model) {

    List<String> transactions = List.of(
        "Food - 500",
        "Travel - 2000",
        "Shopping - 1000"
    );

    model.addAttribute("transactions", transactions);

    return "ListAmount";
}

    @GetMapping("/status")
public String statusPage(Model model) {

    double totalIncome = 5000;
    double totalExpense = 3000;

    double balance = totalIncome - totalExpense;

    model.addAttribute("income", totalIncome);
    model.addAttribute("expense", totalExpense);
    model.addAttribute("balance", balance);

    return "Status";
}

@GetMapping("/listDate")
public String listDatePage(Model model) {

    record Transaction(String description, double amount, String date) {}

    List<Transaction> transactions = List.of(
        new Transaction("Food", 500, "2026-03-20"),
        new Transaction("Travel", 2000, "2026-03-22"),
        new Transaction("Shopping", 1000, "2026-03-21")
    );

    model.addAttribute("transactions", transactions);

    return "ListDate";
}

    @GetMapping("/exit")
    public String exitPage() {
        return "Exit";
    }
}