package com.jennifer.hackathon.controller;

import java.time.LocalDate;
import java.util.UUID;
import com.jennifer.hackathon.service.*;
import com.jennifer.hackathon.enumType.TransactionType;
import com.jennifer.hackathon.model.Transaction;
import com.jennifer.hackathon.repositry.TransactionRepository;

public class AppController {
    private TransactionRepository transactionrepo = new TransactionRepository();
    private BudgetService budgetService = new BudgetService(transactionrepo);
    // TODO: CHALLENGE 5 (Part A/B) - Define and wire Service & Category Repo

    public void handleAddTransaction(String desc, String amtStr, String cat, String typeStr) {
        try {

            double amount = Double.parseDouble(amtStr);
            TransactionType transactionType = TransactionType.valueOf(typeStr);
            UUID uuid = UUID.randomUUID();
            String id = String.valueOf(uuid);
            LocalDate date = LocalDate.now();

            Transaction t = new Transaction(id, date, desc, amount, cat, transactionType);

            budgetService.addTransaction(t);
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
