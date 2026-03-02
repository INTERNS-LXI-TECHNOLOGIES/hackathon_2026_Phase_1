package com.example.budgetmanagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.budgetmanagement.enumtype.TransactionType;
import com.example.budgetmanagement.model.*;
import com.example.budgetmanagement.service.*;
import com.example.budgetmanagement.repository.*;

public class Appcontroller {

    private final TransactionRepository transactionrepo;
    private final CategoryRepository categoryrepo;
    private final BudgetService budgetService;
    private final TransactionService transactionService;
    private final CategoryService categoryService;

    // TODO: Constructor injection for repositories and service
    public Appcontroller(TransactionRepository transactionrepo,
            CategoryRepository categoryrepo,
            BudgetService budgetService, TransactionService transactionService, CategoryService categoryService) {
        this.transactionrepo = transactionrepo;
        this.categoryrepo = categoryrepo;
        this.budgetService = budgetService;
        this.transactionService = transactionService;
        this.categoryService = categoryService;

    }

    // TODO: Parse input values, create Transaction object, call service layer
    public void handleAddTransaction(String desc, String amtStr, String cat, String typeStr) {
try{
        UUID transcationId=UUID.randomUUID();
        String id=String.valueOf(transcationId);
        double amount = Double.parseDouble(amtStr);
        TransactionType enumtype = TransactionType.valueOf(typeStr);
        LocalDate date = LocalDate.now();
        Transaction transaction=new Transaction(id,date,desc,amount,cat,enumtype);
        transactionService.addTransaction(transaction);
        System.out.println("Transcation recorded:");
    }catch(Exception e){
            System.err.println("Controller Error: " + e.getMessage());
    }
    }

    // TODO: Parse limit, create Category object, save using repository
    public void addCategory(String name, String limitStr) {
    }

    // TODO: Call service method to fetch transactions sorted by amount
    /*
     * public void listTransactionsByAmount(boolean sortByAmount) {
     * List<Transaction> transactions =
     * transactionService.getTransactionsSortedByAmount(sortByAmount);
     * }
     */

    // TODO: Call service method to fetch transactions sorted by date
    /*
     * public void listTransactionsByDate(boolean sortByDate) {
     * List<Transaction> transactions =
     * transactionService.fetchAllSortedByDate(sortByDate);
     * }
     */

    // TODO: Call advanced analytics methods from service and display results
    public void showAdvancedStats() {
    }

    // TODO: Call summary and partitioning methods from service and display
    // dashboard
    public void showDashboard() {
    }
}