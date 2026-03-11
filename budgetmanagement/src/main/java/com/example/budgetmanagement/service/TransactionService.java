package com.example.budgetmanagement.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.example.budgetmanagement.model.*;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.budgetmanagement.repository.CategoryRepository;
import com.example.budgetmanagement.repository.TransactionRepository;
import com.example.budgetmanagement.enumtype.TransactionType;
import com.example.budgetmanagement.exception.*;

//import com.example.budgetmanagement.exception.DataPersistenceException;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepo;
    @Autowired
    private CategoryRepository categoryRepository;

    // TODO: Constructor Injection

    // TODO: Add transaction + budget validation check
    public Transaction addTransaction(Transaction t) throws DataPersistenceException {
        double newAmount = t.getAmount();
        List<Category> category = categoryRepository.findAll();
        if (t.getType() == TransactionType.EXPENSE) {
            Category c = category.stream()
                    .filter(n -> n.getName().equals(t.getCategoryName()))
                    .findFirst()
                    .orElseThrow(() -> new BudgetException("category not found"));
            double budgetLimit = c.getBudgetLimit();
            List<Transaction> transaction = transactionRepo.findAll();

            double existingAmount = transaction.stream()
                    .filter(k -> k.getType() == TransactionType.EXPENSE)
                    .filter(n -> n.getCategoryName().equals(t.getCategoryName()))
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            double userAmount = existingAmount + newAmount;
            if (userAmount > budgetLimit) {
                throw new BudgetException("Budget limit exceeded: " + t.getCategoryName());
            }

        }
        return transactionRepo.save(t);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    // TODO: Sorting by amount
    public List<Transaction> getTransactionsSortedByAmount() {
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream()
                .sorted((a1, a2) -> Double.compare(a1.getAmount(), a2.getAmount()))
                .toList();

    }

    // TODO: Sorting by date
    public List<Transaction> fetchAllSortedByDate() {
       List<Transaction> transactions= transactionRepo.findAll();
      return transactions.stream()
       .sorted((d1,d2)->(d1.getDate().compareTo(d2.getDate())))
        .toList();
    }

    // TODO: Filter transactions
    public List<Transaction> filterTransactions(Predicate<Transaction> filter) {
        return List.of();
    }

    // TODO: #15 Highest expense
    
}