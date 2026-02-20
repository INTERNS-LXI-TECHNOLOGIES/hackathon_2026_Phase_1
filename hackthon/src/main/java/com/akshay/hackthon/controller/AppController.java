 package com.akshay.hackthon.controller;
import java.util.*;
import com.akshay.hackthon.service.BudgetService;
import com.akshay.hackthon.model.*;
import java.time.LocalDate;
import java.time.*;
import java.util.UUID;


 public  class AppController {
   
    // TODO: CHALLENGE 5 (Part A/B) - Define and wire Service & Category Repo

       BudgetService budgetService;
        
       public AppController (BudgetService budgetService){

        this.budgetService=budgetService;

          

       }
         


       
       

    public void handleAddTransaction(String desc, String amtStr, String cat, String typeStr) {

        LocalDate date = LocalDate.now();
       
        String id = String.valueOf(UUID.randomUUID());
         
       double amt = Double.parseDouble(amtStr);
    TransactionType type = TransactionType.valueOf(typeStr.toUpperCase());

        try {

            

            
            Transaction transaction = new Transaction(id,date,desc,amt,cat,type);

            

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
