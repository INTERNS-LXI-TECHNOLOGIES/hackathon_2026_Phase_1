package com.harikesh.controller;
// ============================================================================
// 5. CONTROLLER LAYER (Intended: com.{name}.hackathon.controller)
// ============================================================================

import java.time.LocalDate;
import java.util.List;

import com.harikesh.entity.Category;
import com.harikesh.entity.Transaction;
import com.harikesh.entity.TransactionType;
import com.harikesh.service.BudgetService;

public class AppController {
   

    private BudgetService budgetService;

    public AppController(BudgetService budgetService){
         this.budgetService= budgetService;
        
    }
   


    public void handleAddTransaction(String desc, String amtStr, String cat, String typeStr, LocalDate date) {


        double amount = Double.parseDouble(amtStr);
        try {
             Transaction transaction = new Transaction(
                java.util.UUID.randomUUID().toString(),
                date,
                desc,
                amount,
                cat,
                TransactionType.valueOf(typeStr.toUpperCase())
        );
            System.out.println("Transaction recorded.");
            System.out.println(date);
            budgetService.addTransaction(transaction);
        } catch (Exception e) {
            System.err.println("Controller Error: " + e.getMessage());
        }
    }

    public void addCategory(Category c) {
        try {

           // Category cg = new Category(name, 0);
            System.out.println("Category");
            budgetService.addCategory(c);
            
            
        } catch (Exception e) {
            System.err.println("Category Error: " + e.getMessage());
        }
    }

   

    public void showAdvancedStats() {
        System.out.print("\n--- ADVANCED FINANCIAL INSIGHTS ---");
       
        System.out.println("------------------------------------");
        
        System.out.println(budgetService.getExpenseStatistics());
        System.out.println(budgetService.getHighestExpense());
    }

    public void showDashboard() {
        System.out.println("\n--- BUDGET DASHBOARD ---");
       
        System.out.println("-------------------------");
    }

    public void fetchAllSortedByDate(boolean sortByAmount) {
        if(sortByAmount == false){
             List <Transaction> sortedDate = budgetService.fetchAllSortedByDate();
        System.out.println("Date");
        sortedDate.forEach(System.out::println);
        }else{
            List<Transaction> sortedAmount = budgetService.getTransactionsSortedByAmount();
            sortedAmount.forEach(System.out::println);
        }
       
       
    }
}