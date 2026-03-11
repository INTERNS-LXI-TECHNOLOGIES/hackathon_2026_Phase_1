package com.example.budgetmanagement.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.budgetmanagement.enumtype.TransactionType;
import com.example.budgetmanagement.model.Transaction;
import com.example.budgetmanagement.repository.TransactionRepository;

@Service
public class AnalyticsService {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionRepo;

    

    // TODO: Expense statistics
    // CHALLENGE 13: STATISTICAL SUMMARY
    public DoubleSummaryStatistics getExpenseStatistics() {
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream()
                .filter(e -> e.getType() == TransactionType.EXPENSE)
                .mapToDouble(a -> a.getAmount())
                .summaryStatistics();

        // TODO: Implement using .stream().filter(expenses).mapToDouble(t ->
        // t.amount()).summaryStatistics()

    }

    public Optional<Transaction> getHighestExpense() {
        List<Transaction> transactions=transactionRepo.findAll();
       return transactions.stream()
        .filter(e->e.getType()==TransactionType.EXPENSE)
        .max(Comparator.comparingDouble(Transaction::getAmount));
        
    }

    // TODO: High value check
    public boolean hasHighValueTransaction(String category, double threshold) {
        return false;
    }

    // TODO: Unique descriptions
    public Set<String> getUniqueDescriptions() {
        return Set.of();
    }


 public String getCategoryReport() {
        // CHALLENGE 16: DATA JOINING
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream()
                .map(Transaction::getCategoryName)
                .distinct()
                .sorted().collect(Collectors.joining(","));

        // TODO: Implement using
        // .stream().map(...).distinct().sorted().collect(Collectors.joining(", "))

    }




}