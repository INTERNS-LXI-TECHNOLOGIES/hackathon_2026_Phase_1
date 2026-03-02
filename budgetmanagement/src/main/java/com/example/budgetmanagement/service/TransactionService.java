package com.example.budgetmanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import com.example.budgetmanagement.model.Transaction;
import com.example.budgetmanagement.repository.TransactionRepository;
import com.example.budgetmanagement.exception.*;
//import com.example.budgetmanagement.exception.DataPersistenceException;

public class TransactionService {

    private final TransactionRepository transactionRepo;

    // TODO: Constructor Injection
    public TransactionService(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    // TODO: Add transaction + budget validation check
    public void addTransaction(Transaction t) throws DataPersistenceException {
      List<Transaction> transactions= transactionRepo.findAll();
    } 

    // TODO: Sorting by amount
    public List<Transaction> getTransactionsSortedByAmount() {
        return List.of();
    }

    // TODO: Sorting by date
    public List<Transaction> fetchAllSortedByDate() {
        return List.of();
    }

    // TODO: Filter transactions
    public List<Transaction> filterTransactions(Predicate<Transaction> filter) {
        return List.of();
    }

    // TODO: Highest expense
    public Optional<Transaction> getHighestExpense() {
        return Optional.empty();
    }

    // TODO: High value check
    public boolean hasHighValueTransaction(String category, double threshold) {
        return false;
    }

    // TODO: Unique descriptions
    public Set<String> getUniqueDescriptions() {
        return Set.of();
    }
}