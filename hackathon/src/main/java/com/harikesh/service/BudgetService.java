package com.harikesh.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.harikesh.controller.AppController;
import com.harikesh.entity.Category;
import com.harikesh.entity.Transaction;
import com.harikesh.entity.TransactionType;
import com.harikesh.exception.DataPersistenceException;
import com.harikesh.repository.CategoryRepository;
import com.harikesh.repository.TransactionRepository;

// ============================================================================
// 4. SERVICE LAYER (Intended: com.{name}.hackathon.service)
// ============================================================================

public class BudgetService {


    private List<Transaction> transaction = new ArrayList<>();
    private TransactionRepository transactionRepository;
    CategoryRepository cr= new CategoryRepository();
    private CategoryRepository categoryRepository;

    public BudgetService() {
        this.transactionRepository = new TransactionRepository();
        this.categoryRepository = new CategoryRepository();
    }

   
    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        // CHALLENGE 12: PARTITIONING DATA
        // TODO: Implement using .stream().collect(Collectors.partitioningBy(t -> t.type() == TransactionType.INCOME))

        return transactionRepository.findAll().stream()
        .filter(t -> t.type() == TransactionType.INCOME)
        .collect(Collectors.partitioningBy(t -> t.type() == TransactionType.INCOME));
        
    }

    public DoubleSummaryStatistics getExpenseStatistics() {

    return transactionRepository.findAll().stream()
            .filter(t -> t.type() == TransactionType.EXPENSE)
            .mapToDouble(Transaction::amount)
            .summaryStatistics();
            
}
    public boolean hasHighValueTransaction(String category, double threshold) {
        // CHALLENGE 14: EXISTENCE & THRESHOLDS
        // TODO: Implement using .stream().anyMatch(...)


     return  transactionRepository.findAll().stream()
     .filter(c -> c.categoryName().equals(category))
     .anyMatch(c -> c.amount()> threshold);

       
        //return false;
    }

    public Optional<Transaction> getHighestExpense() {
       // CHALLENGE 15: TOP EXPENSE FINDER
        // TODO: Implement using .stream().filter(expenses).max(Comparator.comparingDouble(...))
       return transactionRepository.findAll().stream()
       .filter(h -> h.type() == TransactionType.EXPENSE)
.max(Comparator.comparingDouble(v ->v.amount() ));
       
        // return Optional.empty();
    }

    public String getCategoryReport() {

        // CHALLENGE 16: DATA JOINING
        return transactionRepository.findAll().stream()
        .map(Transaction:: categoryName)
        .distinct()
        .sorted()
        .collect(Collectors.joining(", "));


        
    }

    public List<Transaction> filterTransactions(Predicate<Transaction> filter) {
         // TODO: CHALLENGE 9 - Implementation needed
        return transactionRepository.findAll().stream()
        .filter(filter)
        .collect(Collectors.toList());
    }

    public Set<String> getUniqueDescriptions() {
         // TODO: CHALLENGE 10 - Implementation needed
        return transactionRepository.findAll().stream()
        .map(Transaction :: description)
        .collect(Collectors.toSet());
    }

    public void addTransaction(Transaction t) throws DataPersistenceException {
        if (t.type() == TransactionType.EXPENSE) {
            transactionRepository.save(t);
        }
        
    }

    public List<Transaction> getTransactionsSortedByAmount() {
       return transactionRepository.findAll().stream()
       .sorted(Comparator.comparing(Transaction:: amount).reversed())
       .toList();
    }
    
  public List<Transaction> fetchAllSortedByDate() {
    return transactionRepository.findAll().stream()
    .sorted(Comparator.comparing(Transaction:: date).reversed())
    .toList();
    

}


    public String getGoalStatus() { 
        // TODO: CHALLENGE 2 - Implement calculation (Income - Expense) vs Goal
        double totalIncome = transactionRepository.findAll().stream()
            .filter(t -> t.type() == TransactionType.INCOME)
            .mapToDouble(Transaction::amount)
            .sum();

    double totalExpense = transactionRepository.findAll().stream()
            .filter(t -> t.type() == TransactionType.EXPENSE)
            .mapToDouble(Transaction::amount)
            .sum();

    double net = totalIncome - totalExpense;

    double goalAmount = 50000; // example goal

    if (net >= goalAmount) {
        return "Goal Achieved ";
    } else {
        return "Need ₹" + (goalAmount - net) + " more to reach goal.";
    }

    }

    public Map<String, Double> getSpendingByCategory() {
        
        // TODO: CHALLENGE 4 - Implement groupingBy
        return transactionRepository.findAll().stream()
        .filter(g -> g.type() == TransactionType.EXPENSE)
        .collect(Collectors.groupingBy(Transaction::categoryName, Collectors.summingDouble(Transaction:: amount)));
    }

    public void addCategory(Category c ) throws DataPersistenceException {
        try{
        cr.save(c);
        }catch(DataPersistenceException e){
            e.getMessage();
        }
    }
}
