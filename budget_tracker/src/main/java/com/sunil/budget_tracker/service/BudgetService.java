package com.sunil.budget_tracker.service;

import com.sunil.budget_tracker.exception.DataPersistenceException;
import com.sunil.budget_tracker.model.Transaction;
import com.sunil.budget_tracker.model.TransactionType;

import java.util.stream.Collectors;


import java.util.*;
import java.util.function.Predicate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sunil.budget_tracker.repository.TransactionRepository;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private TransactionRepository transactionRepository;


   
    // TODO: CHALLENGE 5 (Part D/E) - Define repos and implement constructor for wiring

    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        // CHALLENGE 12: PARTITIONING DATA
        // TODO: Implement using .stream().collect(Collectors.partitioningBy(t -> t.type() == TransactionType.INCOME))
        return new HashMap<>();
    }

    public DoubleSummaryStatistics getExpenseStatistics() {
        // CHALLENGE 13: STATISTICAL SUMMARY
        // TODO: Implement using .stream().filter(expenses).mapToDouble(t -> t.amount()).summaryStatistics()
        return new DoubleSummaryStatistics();
    }

    public boolean hasHighValueTransaction(String category, double threshold) {
        // CHALLENGE 14: EXISTENCE & THRESHOLDS
        // TODO: Implement using .stream().anyMatch(...)
        return false;
    }

    public Optional<Transaction> getHighestExpense() {
        // CHALLENGE 15: TOP EXPENSE FINDER
        // TODO: Implement using .stream().filter(expenses).max(Comparator.comparingDouble(...))
        return Optional.empty();
    }

    public String getCategoryReport() {
        // CHALLENGE 16: DATA JOINING
        // TODO: Implement using .stream().map(...).distinct().sorted().collect(Collectors.joining(", "))
        return "";
    }

    public List<Transaction> filterTransactions(Predicate<Transaction> filter) {
        // TODO: CHALLENGE 9 - Implementation needed
        return null;
    }

    public Set<String> getUniqueDescriptions() {
        // TODO: CHALLENGE 10 - Implementation needed
        return new HashSet<>();
    }

    public void addTransaction(Transaction t) {


        transactionRepository.save(t);

        if (t.getType() == TransactionType.EXPENSE) {



            // TODO: CHALLENGE 3 - Implement budget ceiling check
        }
        // TODO: CHALLENGE 5 - Save via transRepo
    }

    public List<Transaction> getTransactionsSortedByAmount() {
               
             List<Transaction> transactions =   transactionRepository.findAll();

           
        return  transactions.stream()     
                         
                         .sorted(Comparator.comparing(n ->n.getAmount()))
                         .toList();


    }

  

    public List<Transaction> fetchAllSortedByDate() {

           List<Transaction> transactions =  transactionRepository.findAll();
                            
                                      Collections.sort(transactions);
                                       
                    
    return transactions;

        // TODO: CHALLENGE 7 - Implement sorting
        
    }

    public String getGoalStatus() {
        // TODO: CHALLENGE 2 - Implement calculation (Income - Expense) vs Goal
        return "Pending...";
    }

    public Map<String, Double> getSpendingByCategory() {
        // TODO: CHALLENGE 4 - Implement groupingBy
        return new HashMap<>();
    }
}
