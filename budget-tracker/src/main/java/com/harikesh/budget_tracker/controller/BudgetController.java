package com.harikesh.budget_tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harikesh.budget_tracker.entity.Transaction;
import com.harikesh.budget_tracker.entity.Category;
import com.harikesh.budget_tracker.service.BudgetService;
import com.harikesh.budget_tracker.service.CategoryService;

import com.harikesh.budget_tracker.exception.DataPersistenceException;

@Controller
public class BudgetController {

    private final BudgetService budgetService;
    private final CategoryService categoryService;

    public BudgetController(BudgetService budgetService, CategoryService categoryService) {
        this.budgetService = budgetService;
        this.categoryService = categoryService;
    }


//    @GetMapping("/login")
// public String loginPage() {
//     return "login"; // login.html
// }

    @GetMapping("/")
    public String home() {
        return "menu";
    }

    @GetMapping("/addTransaction")
    public String addTransactionPage() {
        return "AddTransaction";
    }

    @PostMapping("/addTransaction")
    public String saveTransaction(
            @RequestParam String desc,
            @RequestParam double amt,
            @RequestParam String cat,
            @RequestParam String type
    ) throws DataPersistenceException {

        Transaction t = new Transaction(desc, amt, cat, type);
        budgetService.addTransaction(t);

        return "redirect:/";
    }

    @GetMapping("/AddCategory")
    public String showCategoryPage() {
        return "AddCategory";
    }

    @PostMapping("/AddCategory")
    public String saveCategory(
            @RequestParam String name,
            @RequestParam double spendingLimit) {

        Category category = new Category(name, spendingLimit);
        categoryService.addCategory(category);

        return "redirect:/";
    }


    @GetMapping("/listDate")
public String listByDate(Model model) {

    List<Transaction> transactions = budgetService.fetchAllSortedByDate();

    model.addAttribute("transactions", transactions);

    return "ListDate";
}

@GetMapping("/listAmount")
public String listByAmount(Model model){

    List<Transaction> transactions = budgetService.getTransactionsSortedByAmount();

    model.addAttribute("transactions", transactions);

    return "ListAmount";
}

@GetMapping("/stats")
public String showStats(Model model){

    Map<String, Double> stats = budgetService.getExpenseStatistics();

    model.addAttribute("income", stats.get("income"));
    model.addAttribute("expense", stats.get("expense"));
    model.addAttribute("balance", stats.get("balance"));

    return "Stats";
}

@GetMapping("/summary")
public String showSummary(Model model){

    Map<String, Double> summary = budgetService.getPartitionedTransactions();

    model.addAttribute("income", summary.get("income"));
    model.addAttribute("expense", summary.get("expense"));
    model.addAttribute("balance", summary.get("balance"));

    return "Summary";
}

@GetMapping("/exit")
public String exitApp() {
    return "exit";
}



}