package com.example.budgetmanagement.controller;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.budgetmanagement.enumtype.TransactionType;
import com.example.budgetmanagement.model.*;
import com.example.budgetmanagement.service.*;
import com.example.budgetmanagement.repository.*;
import com.example.budgetmanagement.exception.*;
import org.springframework.ui.Model;
import java.util.Optional;

@Controller
@RequestMapping("/api/transaction")

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/homepage")
    public String homePage() {
        return "home";
    }

    @GetMapping("/addpage")
    public String addTranscationPage(Model model) {
        Transaction t = new Transaction();

        model.addAttribute("transaction", t);
        return "addtransaction";
    }

    // TODO: Parse input values, create Transaction object, call service layer
    @PostMapping("/add")
    public String handleAddTransaction(Transaction transaction, Model model) throws DataPersistenceException {
        try {
            transactionService.addTransaction(transaction);
            return "redirect:/api/transaction/viewaddtransaction";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "addtransaction";
        }

    }

    @GetMapping("/viewaddtransaction")
    public String viewTransactions(Model model) {

        List<Transaction> transactions = transactionService.getAllTransactions();

        model.addAttribute("transactions", transactions);

        return "viewaddtransaction";
    }

    // TODO: Call service method to fetch transactions sorted by amount
    @GetMapping("/sortedbyamount")
    public String listTransactionsByAmount(Model model) {
        List<Transaction> sortedAmount = transactionService.getTransactionsSortedByAmount();
        model.addAttribute("t", sortedAmount);
        return "viewsortedamount";
    }

    // TODO: Call service method to fetch transactions sorted by date
    @GetMapping("/sortedbydate")
    public String listTransactionsByDate(Model model) {
        List<Transaction> sortByDate = transactionService.fetchAllSortedByDate();
        model.addAttribute("a", sortByDate);
        return "viewsorteddate";

    }

    @GetMapping("/advancedstatus")
    // TODO: Call advanced analytics methods from service and display results
    public String showAdvancedStats(Model model) {
        System.out.println("\n--- ADVANCED FINANCIAL INSIGHTS ---");
        System.out.println("---------------------------");
        System.out.println("\n--- SummaryStatistics ---");
        // #13
        DoubleSummaryStatistics summary = budgetService.getExpenseStatistics();

        System.out.println("\n--- High Expense ---");
        // #15
        Optional<Transaction> highExpense = transactionService.getHighestExpense();
        // TODO: CHALLENGE 13 & 15 & 16 - Call service methods and display results
        System.out.println("-----Category Report-----------");
        // #16
        String categoryReport = categoryService.getCategoryReport();

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