package com.example.budgetmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.budgetmanagement.service.DashboardService;
import com.example.budgetmanagement.service.TransactionService;
import java.util.Map;
import java.util.List;
import org.springframework.ui.Model;
import com.example.budgetmanagement.model.*;
@Controller
@RequestMapping("api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // TODO: Call summary and partitioning methods from service and display
    // dashboard


     @GetMapping("/dashboardpage")
    public String addCategory() {
        return "home";
    }
    @GetMapping("/board")
    public String showDashboard(Model model) {
        System.out.println("\n--- BUDGET DASHBOARD ---");
        // #4
        Map<String, Double> spendingOnCategory = dashboardService.getSpendingByCategory();

        // #12
        Map<Boolean, List<Transaction>> partitioned = dashboardService.getPartitionedTransactions();

        model.addAttribute("scategory",spendingOnCategory);
        model.addAttribute("partition", partitioned);

        // TODO: CHALLENGE 4 & 12 - Integrate summary and partitioning count
        System.out.println("-------------------------");
        return "viewdashboard";
    }


    
}
