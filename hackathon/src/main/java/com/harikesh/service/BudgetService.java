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

    TransactionRepository tr = new TransactionRepository();
    CategoryRepository cr= new CategoryRepository();
   
    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        // CHALLENGE 12: PARTITIONING DATA
         
        return new HashMap<>();
    }

    public DoubleSummaryStatistics getExpenseStatistics() {

    return tr.findAll().stream()
            .filter(t -> t.type() == TransactionType.EXPENSE)
            .mapToDouble(Transaction::amount)
            .summaryStatistics();
            
}
    public boolean hasHighValueTransaction(String category, double threshold) {
        // CHALLENGE 14: EXISTENCE & THRESHOLDS
       
        return false;
    }

    public Optional<Transaction> getHighestExpense() {
        // CHALLENGE 15: TOP EXPENSE FINDER
       return tr.findAll().stream()
       .filter(h -> h.type() == TransactionType.EXPENSE)
.max(Comparator.comparingDouble(v ->v.amount() ));
       
        // return Optional.empty();
    }

    public String getCategoryReport() {

        // CHALLENGE 16: DATA JOINING
        
        return "";
    }

    public List<Transaction> filterTransactions(Predicate<Transaction> filter) {
        
        return new ArrayList<>();
    }

    public Set<String> getUniqueDescriptions() {
       
        return new HashSet<>();
    }

    public void addTransaction(Transaction t) throws DataPersistenceException {
        if (t.type() == TransactionType.EXPENSE) {
            tr.save(t);
        }
        
    }

    public List<Transaction> getTransactionsSortedByAmount() {
       return tr.findAll().stream()
       .sorted(Comparator.comparing(Transaction:: amount).reversed())
       .toList();
    }
    
  public List<Transaction> fetchAllSortedByDate() {
    return tr.findAll().stream()
    .sorted(Comparator.comparing(Transaction:: date).reversed())
    .toList();
    

}


    public String getGoalStatus() { 
       
        return "Pending...";
    }

    public Map<String, Double> getSpendingByCategory() {
        
        return new HashMap<>();
    }

    public void addCategory(Category c ) throws DataPersistenceException {
        try{
        cr.save(c);
        }catch(DataPersistenceException e){
            e.getMessage();
        }
    }
}
