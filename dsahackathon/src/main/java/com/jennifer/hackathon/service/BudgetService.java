package com.jennifer.hackathon.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.jennifer.hackathon.model.Category;
import com.jennifer.hackathon.model.Transaction;
import com.jennifer.hackathon.repositry.CategoryRepository;
import com.jennifer.hackathon.repositry.TransactionRepository;
import com.jennifer.hackathon.enumType.TransactionType;
import com.jennifer.hackathon.exceptions.DataPersistenceException;
import com.jennifer.hackathon.exceptions.BudgetException;

public class BudgetService {

    private final TransactionRepository transactionrepo;
    private final CategoryRepository categoryrepo;
    List<Transaction> transactions = new ArrayList<>();

    public BudgetService(TransactionRepository transactionrepo, CategoryRepository categoryrepo) {
        this.transactionrepo = transactionrepo;
        this.categoryrepo = categoryrepo;
    }
    // TODO: CHALLENGE 5 (Part D/E) - Define repos and implement constructor for
    // wiring

    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        // CHALLENGE 12: PARTITIONING DATA
        // TODO: Implement using .stream().collect(Collectors.partitioningBy(t ->
        // t.type() == TransactionType.INCOME))
        return new HashMap<>();
    }

    public DoubleSummaryStatistics getExpenseStatistics() {
        // CHALLENGE 13: STATISTICAL SUMMARY
        // TODO: Implement using .stream().filter(expenses).mapToDouble(t ->
        // t.amount()).summaryStatistics()
        return new DoubleSummaryStatistics();
    }

    public boolean hasHighValueTransaction(String category, double threshold) {
        // CHALLENGE 14: EXISTENCE & THRESHOLDS
        // TODO: Implement using .stream().anyMatch(...)
        return false;
    }

    public Optional<Transaction> getHighestExpense() {
        // CHALLENGE 15: TOP EXPENSE FINDER
        // TODO: Implement using
        // .stream().filter(expenses).max(Comparator.comparingDouble(...))
        return Optional.empty();
    }

    public String getCategoryReport() {
        // CHALLENGE 16: DATA JOINING
        // TODO: Implement using
        // .stream().map(...).distinct().sorted().collect(Collectors.joining(", "))
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

        double newAmount = t.amount();
        List<Category> category = categoryrepo.findAll();
        if (t.type() == TransactionType.EXPENSE) {
            Category c = category.stream()
                    .filter(n -> n.name().equals(t.categoryName()))
                    .findFirst()
                    .orElseThrow(() -> new BudgetException("category not found"));
            double budgetLimit = c.budgetLimit();
            List<Transaction> transaction = transactionrepo.findAll();

            double existingAmount = transaction.stream()
                    .filter(k -> k.type() == TransactionType.EXPENSE)
                    .filter(n -> n.categoryName().equals(t.categoryName()))
                    .mapToDouble(Transaction::amount)
                    .sum();

            double userAmount = existingAmount + newAmount;
            if (userAmount > budgetLimit) {
                throw new BudgetException("Budget limit exceeded: " + t.categoryName());
            }

            // TODO: CHALLENGE 3 - Implement budget ceiling check
        }
        transactionrepo.save(t);
        // TODO: CHALLENGE 5 - Save via transRepo

    }

    public List<Transaction> getTransactionsSortedByAmount(boolean amount) {
        List<Transaction> t = transactionrepo.findAll();
        List<Transaction> transactionsAmount = t.stream()
                .sorted((a1, a2) -> amount
                        ? Double.compare(a1.amount(), a2.amount())
                        : Double.compare(a2.amount(), a1.amount()))
                .toList();
        // TODO: CHALLENGE 7 - Implement sorting
        return transactionsAmount;
    }

    public List<Transaction> fetchAllSortedByDate(boolean date) {
        List<Transaction> t = transactionrepo.findAll();
        List<Transaction> s = t.stream()
                .sorted((t1, t2) -> date
                        ? t1.date().compareTo(t2.date())
                        : t2.date().compareTo(t1.date()))
                .toList();
        // TODO: CHALLENGE 7 - Implement sorting
        return s;
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