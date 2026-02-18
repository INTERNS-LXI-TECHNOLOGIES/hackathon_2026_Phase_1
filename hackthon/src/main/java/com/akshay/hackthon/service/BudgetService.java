package com.akshay.hackthon.service;

import java.util.function.Predicate;
import java.util.*;
import com.akshay.hackthon.model.TransactionType;

import com.akshay.hackthon.model.Transaction;

import com.akshay.hackthon.exception.*;

public class BudgetService {
   
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

    public Optional<TransactionType> getHighestExpense() {
        // CHALLENGE 15: TOP EXPENSE FINDER
        // TODO: Implement using .stream().filter(expenses).max(Comparator.comparingDouble(...))
        return Optional.empty();
    }

    public String getCategoryReport() {
        // CHALLENGE 16: DATA JOINING
        // TODO: Implement using .stream().map(...).distinct().sorted().collect(Collectors.joining(", "))
        return "";
    }

    public List<TransactionType> filterTransactions(Predicate<TransactionType> filter) {
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

    public List<TransactionType> getTransactionsSortedByAmount() {
        // TODO: CHALLENGE 7 - Implement sorting
        return new ArrayList<>();
    }

    public List<TransactionType> fetchAllSortedByDate() {
        // TODO: CHALLENGE 7 - Implement sorting
        return new ArrayList<>();
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

