package com.example.budgetmanagement.service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.budgetmanagement.model.*;
import com.example.budgetmanagement.repository.CategoryRepository;
import com.example.budgetmanagement.repository.TransactionRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private TransactionRepository transactionRepo;

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

    // TODO: Group spending by category
    public Map<String, Double> getSpendingByCategory() {
        return Map.of();
    }
}