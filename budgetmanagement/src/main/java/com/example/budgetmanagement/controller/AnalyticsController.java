package com.example.budgetmanagement.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.DoubleSummaryStatistics;
import java.util.Optional;

import com.example.budgetmanagement.model.Transaction;
import com.example.budgetmanagement.service.AnalyticsService;
import com.example.budgetmanagement.service.TransactionService;
import com.example.budgetmanagement.service.CategoryService;
@Controller
@RequestMapping("/api/analytics")
 public class AnalyticsController{
    @Autowired
    private AnalyticsService analyticsService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CategoryService categoryService;

@GetMapping("/analyticspage")
    public String homePage() {
        return "home";
    }


@GetMapping("/advancedstatus")
    // TODO: Call advanced analytics methods from service and display results
    public String showAdvancedStats(Model model) {
        System.out.println("\n--- ADVANCED FINANCIAL INSIGHTS ---");
        System.out.println("---------------------------");
        System.out.println("\n--- SummaryStatistics ---");
        // #13
        DoubleSummaryStatistics summary = analyticsService.getExpenseStatistics();

        System.out.println("\n--- High Expense ---");
        // #15
        Optional<Transaction> highExpense = analyticsService.getHighestExpense();
        // TODO: CHALLENGE 13 & 15 & 16 - Call service methods and display results
        System.out.println("-----Category Report-----------");
        // #16
        String categoryReport = analyticsService.getCategoryReport();

        model.addAttribute("total", summary.getSum());
        model.addAttribute("average", summary.getAverage());
        model.addAttribute("max", summary.getMax());

        model.addAttribute("min", summary.getMin());

        model.addAttribute("count", summary.getCount());

        model.addAttribute("highExpense", highExpense.orElse(null));
        model.addAttribute("categoryReport", categoryReport);
        return "viewsummarystatistics";
    }
}
