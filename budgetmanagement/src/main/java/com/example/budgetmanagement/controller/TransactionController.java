package com.example.budgetmanagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.budgetmanagement.enumtype.TransactionType;
import com.example.budgetmanagement.model.*;
import com.example.budgetmanagement.service.*;
import com.example.budgetmanagement.repository.*;
import com.example.budgetmanagement.exception.*;

@RestController
@RequestMapping("api/transaction")

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    // TODO: Parse input values, create Transaction object, call service layer
    @PostMapping
    public Transaction handleAddTransaction(@RequestBody Transaction transaction) throws DataPersistenceException{
        
         return  transactionService.addTransaction(transaction);

        

    }
    // TODO: Call service method to fetch transactions sorted by amount
    /*
     * public void listTransactionsByAmount(boolean sortByAmount) {
     * List<Transaction> transactions =
     * transactionService.getTransactionsSortedByAmount(sortByAmount);
     * }
     */

    // TODO: Call service method to fetch transactions sorted by date
    /*
     * public void listTransactionsByDate(boolean sortByDate) {
     * List<Transaction> transactions =
     * transactionService.fetchAllSortedByDate(sortByDate);
     * }
     */

    // TODO: Call advanced analytics methods from service and display results
    public void showAdvancedStats() {
    }

}