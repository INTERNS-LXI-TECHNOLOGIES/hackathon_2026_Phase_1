package com.example.budgetmanagement.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.budgetmanagement.enumtype.TransactionType;
import com.example.budgetmanagement.model.Transaction;
import com.example.budgetmanagement.repository.TransactionRepository;

@Service
public class BudgetService {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionrepo;

    // TODO: Partition income & expense
    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        return Map.of();
    }

    // TODO: Expense statistics
    // CHALLENGE 13: STATISTICAL SUMMARY
    public DoubleSummaryStatistics getExpenseStatistics() {
        List<Transaction> transactions = transactionrepo.findAll();
        return transactions.stream()
                .filter(e -> e.getType() == TransactionType.EXPENSE)
                .mapToDouble(a -> a.getAmount())
                .summaryStatistics();

        // TODO: Implement using .stream().filter(expenses).mapToDouble(t ->
        // t.amount()).summaryStatistics()

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