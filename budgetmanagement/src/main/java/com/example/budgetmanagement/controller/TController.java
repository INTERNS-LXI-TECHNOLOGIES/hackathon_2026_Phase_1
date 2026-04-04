package com.example.budgetmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.budgetmanagement.exception.DataPersistenceException;
import com.example.budgetmanagement.model.Transaction;
import com.example.budgetmanagement.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TController {

    @Autowired
    private TransactionService transactionService;

    // Add transaction
    @PostMapping("/addt")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) throws DataPersistenceException{
        transactionService.addTransaction(transaction);
        return ResponseEntity.ok("Transaction added successfully!");
    }

    // Get all transactions
    @GetMapping("/allt")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // Get sorted by amount
    @GetMapping("/sortedbyamountt")
    public ResponseEntity<List<Transaction>> getSortedByAmount() {
        List<Transaction> transactions = transactionService.getTransactionsSortedByAmount();
        return ResponseEntity.ok(transactions);
    }

    // Get sorted by date
    @GetMapping("/sortedbydatet")
    public ResponseEntity<List<Transaction>> getSortedByDate() {
        List<Transaction> transactions = transactionService.fetchAllSortedByDate();
        return ResponseEntity.ok(transactions);
    }
}