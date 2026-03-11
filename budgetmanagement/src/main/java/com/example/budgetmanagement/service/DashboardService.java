package com.example.budgetmanagement.service;
import com.example.budgetmanagement.enumtype.TransactionType;
import com.example.budgetmanagement.model.*;
import com.example.budgetmanagement.repository.TransactionRepository;
import java.util.stream.Collectors;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    private TransactionRepository transactionRepo;

    public Map<String, Double> getSpendingByCategory() {
        List<Transaction> transactions=transactionRepo.findAll();
       return transactions.stream()
        .filter(t->t.getType()==TransactionType.EXPENSE)
       .collect(Collectors.groupingBy(Transaction::getCategoryName,Collectors.summingDouble(Transaction::getAmount)));

        // TODO: CHALLENGE 4 - Implement groupingBy
           }


    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        // CHALLENGE 12: PARTITIONING DATA
        List<Transaction> transactions=transactionRepo.findAll();
       return transactions.stream()
        .collect(Collectors.partitioningBy(t->t.getType()==TransactionType.INCOME));
        // TODO: Implement using .stream().collect(Collectors.partitioningBy(t ->
        // t.type() == TransactionType.INCOME))
        
    }


    public String getGoalStatus() {
        // TODO: CHALLENGE 2 - Implement calculation (Income - Expense) vs Goal
        return "Pending...";
    }
}
