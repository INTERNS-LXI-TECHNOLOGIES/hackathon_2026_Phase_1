package com.example.budgetmanagement.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import com.example.budgetmanagement.model.Transaction;
public class BudgetService {

    private final TransactionService transactionService;

    // TODO: Constructor Injection
    public BudgetService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // TODO: Partition income & expense
    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        return Map.of();
    }

    // TODO: Expense statistics
    public DoubleSummaryStatistics getExpenseStatistics() {
        return new DoubleSummaryStatistics();
    }

    // TODO: Goal calculation
    public String getGoalStatus() {
        return "Pending...";
    }

    // TODO: Category report
    public String getCategoryReport() {
        return "";
    }
}