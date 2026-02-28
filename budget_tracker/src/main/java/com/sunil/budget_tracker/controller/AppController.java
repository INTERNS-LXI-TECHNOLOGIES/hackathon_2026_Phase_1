package com.sunil.budget_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.budget_tracker.exception.BudgetException;
import com.sunil.budget_tracker.exception.DataPersistenceException;
import com.sunil.budget_tracker.model.Transaction;
import com.sunil.budget_tracker.service.BudgetService;

@RestController
@RequestMapping("/controller")
public class AppController {
@Autowired
private  BudgetService budgetService;   
   
    // TODO: CHALLENGE 5 (Part A/B) - Define and wire Service & Category Repo
    @PostMapping("/addTransaction")
    public void handleAddTransaction(@RequestBody Transaction transaction) throws DataPersistenceException {

 

        try {
                    budgetService.addTransaction(transaction);

            // TODO: Parse inputs and call service.addTransaction
            System.out.println("Transaction recorded.");
        } catch (Exception e) {
            System.err.println("Controller Error: " + e.getMessage());
        }


        
    }




    public void addCategory(String name, String limitStr) {
        try {
            // TODO: CHALLENGE 5 (Part C) - Wire call to catRepo.save()
            System.out.println("Category added.");
        } catch (Exception e) {
            System.err.println("Category Error: " + e.getMessage());
        }
    }

    public void listTransactions(boolean sortByAmount) {
        // TODO: CHALLENGE 7 - Wire service calls for sorting
    }

    public void showAdvancedStats() {
        System.out.println("\n--- ADVANCED FINANCIAL INSIGHTS ---");
        // TODO: CHALLENGE 13 & 15 & 16 - Call service methods and display results
        System.out.println("------------------------------------");
    }

    public void showDashboard() {
        System.out.println("\n--- BUDGET DASHBOARD ---");
        // TODO: CHALLENGE 4 & 12 - Integrate summary and partitioning count
        System.out.println("-------------------------");
    }
}
