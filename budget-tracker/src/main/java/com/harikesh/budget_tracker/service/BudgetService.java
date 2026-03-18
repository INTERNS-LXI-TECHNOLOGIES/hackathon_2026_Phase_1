package com.harikesh.budget_tracker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.harikesh.budget_tracker.entity.Transaction;
import com.harikesh.budget_tracker.entity.Category;
import com.harikesh.budget_tracker.exception.DataPersistenceException;
import com.harikesh.budget_tracker.repository.BudgetRepository;
import com.harikesh.budget_tracker.repository.CategoryRepository;

@Service
public class BudgetService {
   
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    public BudgetService(BudgetRepository budgetRepository, CategoryRepository categoryRepository){
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
    }

    public void addTransaction(Transaction t) throws DataPersistenceException {
       budgetRepository.save(t);
       System.out.println("Saved in DB: " + t.getDescription());
    }

    public List<Transaction> fetchAllSortedByDate() {

        return budgetRepository.findAll()
                .stream()
                .sorted((t1, t2) -> t2.getDate().compareTo(t1.getDate()))
                .toList();
    }

    public Map<String, Double> getSpendingByCategory() {

        return budgetRepository.findAll()
                .stream()
                .filter(t -> t.getType().equals("EXPENSE"))
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }

    public void addCategory(Category c) {
    categoryRepository.save(c);
}

    
    public List<Transaction> getTransactionsSortedByAmount() {

    return budgetRepository.findAll()
            .stream()
            .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
            .toList();
        
}

public Map<String, Double> getExpenseStatistics(){

    double income = budgetRepository.findAll()
            .stream()
            .filter(t -> t.getType().equalsIgnoreCase("INCOME"))
            .mapToDouble(Transaction::getAmount)
            .sum();

    double expense = budgetRepository.findAll()
            .stream()
            .filter(t -> t.getType().equalsIgnoreCase("EXPENSE"))
            .mapToDouble(Transaction::getAmount)
            .sum();

    double balance = income - expense;
    

    Map<String, Double> stats = new HashMap<>();
    stats.put("income", income);
    stats.put("expense", expense);
    stats.put("balance", balance);

    return stats;
}
  // TODO: CHALLENGE 5 (Part D/E) - Define repos and implement constructor for wiring

  public Map<String, Double> getPartitionedTransactions() {
    
    double income = budgetRepository.findAll()
            .stream()
            .filter(t -> t.getType().equalsIgnoreCase("INCOME"))
            .mapToDouble(Transaction::getAmount)
            .sum();
     
    double expense = budgetRepository.findAll()
            .stream()
            .filter(t -> t.getType().equalsIgnoreCase("EXPENSE"))
            .mapToDouble(Transaction::getAmount)
            .sum();

    double balance = income - expense;

    Map<String, Double> summary = new HashMap<>();

    summary.put("income", income);
    summary.put("expense", expense);
    summary.put("balance", balance);

    return summary;
}
}
   

   

    // public boolean hasHighValueTransaction(String category, double threshold) {
    //     // CHALLENGE 14: EXISTENCE & THRESHOLDS
    //     // TODO: Implement using .stream().anyMatch(...)
    //     return false;
    // }

    // public Optional<Transaction> getHighestExpense() {
    //     // CHALLENGE 15: TOP EXPENSE FINDER
    //     // TODO: Implement using .stream().filter(expenses).max(Comparator.comparingDouble(...))
    //     return Optional.empty();
    // }

    // public String getCategoryReport() {
    //     // CHALLENGE 16: DATA JOINING
    //     // TODO: Implement using .stream().map(...).distinct().sorted().collect(Collectors.joining(", "))
    //     return "";
    // }

    // public List<Transaction> filterTransactions(Predicate<Transaction> filter) {
    //     // TODO: CHALLENGE 9 - Implementation needed
    //     return new ArrayList<>();
    // }

    // public Set<String> getUniqueDescriptions() {
    //     // TODO: CHALLENGE 10 - Implementation needed
    //     return new HashSet<>();
    // }

    


   
    // public String getGoalStatus() {
    //     // TODO: CHALLENGE 2 - Implement calculation (Income - Expense) vs Goal
    //     return "Pending...";
    // }

    

