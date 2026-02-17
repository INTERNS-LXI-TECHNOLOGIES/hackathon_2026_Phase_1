package com.athul.hackathone.service;


import com.athul.hackathone.execption.DataPersistenceException;
import com.athul.hackathone.model.Transaction;
import com.athul.hackathone.model.TransactionType;
import com.athul.hackathone.repo.Repository.CategoryRepository;
import com.athul.hackathone.repo.Repository.TransactionRepository;
import com.athul.hackathone.repo.Repository.UserProfileRepository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BudgetService {

    // TODO: CHALLENGE 5 (Part D/E) - Define repos and implement constructor for wiring
     TransactionRepository transactionRepository ;
     CategoryRepository categoryRepository;
     UserProfileRepository userProfileRepository;
    public BudgetService(TransactionRepository transactionRepository,UserProfileRepository userProfileRepository,CategoryRepository categoryRepository){
        this.transactionRepository= transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userProfileRepository = userProfileRepository;
    }
    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        // CHALLENGE 12: PARTITIONING DATA
        // TODO: Implement using .stream().collect(Collectors.partitioningBy(t -> t.type() == TransactionType.INCOME))
             var trns = transactionRepository.findAll();

        return trns.stream().collect(Collectors.partitioningBy( n -> n.type() == TransactionType.INCOME));
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
        return new ArrayList<>();
    }

    public Set<String> getUniqueDescriptions() {
        // TODO: CHALLENGE 10 - Implementation needed
        return new HashSet<>();
    }
    public void addTransaction(Transaction t) throws DataPersistenceException {
        if (t.type() == TransactionType.EXPENSE) {
            // TODO: CHALLENGE 3 - Implement budget ceiling check

        }
        // TODO: CHALLENGE 5 - Save via transRepo
    }

    public List<Transaction> getTransactionsSortedByAmount() {
        // TODO: CHALLENGE 7 - Implement sorting
        var transAm = transactionRepository.findAll();
               return   transAm.stream()
                         .sorted(Comparator.comparingDouble( Transaction :: amount))
                         .toList();//return new ArrayList<>();
    }

    public List<Transaction> fetchAllSortedByDate() {
        // TODO: CHALLENGE 7 - Implement sorting
          var  transDate = transactionRepository.findAll();

      return  transDate.stream().sorted().toList();
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

