package com.example.budgetmanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.budgetmanagement.model.Transaction;
import com.example.budgetmanagement.repository.TransactionRepository;
import com.example.budgetmanagement.exception.*;
//import com.example.budgetmanagement.exception.DataPersistenceException;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepo;

    // TODO: Constructor Injection

    // TODO: Add transaction + budget validation check
    public Transaction  addTransaction(Transaction t) throws DataPersistenceException {
        List<Transaction> transactions= transactionRepo.findAll();
      return   transactionRepo.save(t);
       
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