package com.sunil.hackathon.service;

import java.util.*;
//import java.util.Locale.Category;


import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.stream.*;
import java.util.function.*;

import com.sunil.hackathon.exception.DataPersistenceException;
import com.sunil.hackathon.model.Transaction;
import com.sunil.hackathon.model.TransactionType;
import com.sunil.hackathon.repository.CategoryRepository;
import com.sunil.hackathon.repository.TransactionRepository;
import com.sunil.hackathon.model.Category;

public class BudgetService {

    private TransactionRepository transactionRepository;
    private CategoryRepository categoryRepository;

    public BudgetService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {

        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;

    }

    // TODO: CHALLENGE 5 (Part D/E) - Define repos and implement constructor for
    // wiring

    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {

        List<Transaction> transactions =  transactionRepository.findAll();

        // CHALLENGE 12: PARTITIONING DATA
      Map<Boolean,List<Transaction>> value  =  transactions.stream()

        .collect(Collectors.partitioningBy(t -> t.type() == TransactionType.INCOME));
        return value;
    }
  




    public DoubleSummaryStatistics getExpenseStatistics() {

        
        List<Transaction> transactions = transactionRepository.findAll();



        // CHALLENGE 13: STATISTICAL SUMMARY
       
    DoubleSummaryStatistics stats = transactions.stream()
                    .filter(n -> n.type() == TransactionType.EXPENSE).mapToDouble(t -> t.amount())
                    .summaryStatistics();
        return stats;
    }


    // not  finished 

    public boolean hasHighValueTransaction(String category, double threshold) {
        // CHALLENGE 14: EXISTENCE & THRESHOLDS
        // TODO: Implement using .stream().anyMatch(...)
        return false;
    }

    public Optional<Transaction> getHighestExpense() {

       List<Transaction> transactions =  transactionRepository.findAll();
        // CHALLENGE 15: TOP EXPENSE FINDER
        // TODO: Implement using
       Optional<Transaction> maxResult = transactions.stream()
                    .filter(n -> n.type() == TransactionType.EXPENSE)
                    .max(Comparator.comparingDouble(n ->n.amount()));

        return maxResult;
    }

    public String getCategoryReport() {
        // CHALLENGE 16: DATA JOINING
        // TODO: Implement using

      List<Transaction> transactions =  transactionRepository.findAll();

       String categoryNames =  transactions.stream()
                    .map(n -> n.categoryName())
                    .distinct()
                    .sorted()
                    .collect(Collectors.joining(", "));

        return categoryNames;
    }

    // not finished 

    public List<Transaction> filterTransactions(Predicate<Transaction> filter) {
        // TODO: CHALLENGE 9 - Implementation needed
        return new ArrayList<>();
    }


     //not finished 
    public Set<String> getUniqueDescriptions() {

        // TODO: CHALLENGE 10 - Implementation needed

        return new HashSet<>();
    }

    public void addTransaction(Transaction t) throws DataPersistenceException {

        double myBudget = 0;

        List<Category> find = categoryRepository.findAll();

        for (Category c : find) {

            myBudget = c.budgetLimit();

        }

        List<Double> listOfTransactions = new ArrayList<>();

        if (t.type() == TransactionType.EXPENSE) {

            double transactionsAmounts = t.amount();

            double reducedBudget = myBudget - transactionsAmounts;

            listOfTransactions.add(reducedBudget);

            System.out.println("Total Cash in Your Account : " + myBudget);

            System.out.println("Now Your Balance - " + listOfTransactions);

            // TODO: CHALLENGE 3 - Implement budget ceiling check

        } else {

            double pluseAmount = t.amount();

            double mySavings = myBudget + pluseAmount;

            listOfTransactions.add(mySavings);

            System.out.println("Total Cash in Your Account : " + myBudget);

            System.out.println("Now Your Balance + " + listOfTransactions);

        }


        if (t.amount() > myBudget) {

            throw new DataPersistenceException("Insufficient Money", null);
        }

        transactionRepository.save(t);

        // TODO: CHALLENGE 5 - Save via transRepo
    }

    public List<Transaction> getTransactionsSortedByAmount() {

    
      
        List<Transaction>  transactionsByAmount =  transactionRepository.amountSort();

       

             
       return  transactionsByAmount.stream()
                                   .sorted((t1,t2) -> Double.compare(t1.amount(), t2.amount()))
                                   .collect(Collectors.toList()); 
        // TODO: CHALLENGE 7 - Implement sorting

    }


    public List<Transaction> fetchAllSortedByDate() {

        // TODO: CHALLENGE 7 - Implement sorting

         List<Transaction> transactionByDate =  transactionRepository.findAllDate();
                      
         return  transactionByDate.stream()
                                .sorted()
                                .collect(Collectors.toList());

                                
    }


    // not finished 
    public String getGoalStatus(){

    return null;

        // TODO: CHALLENGE 2 - Implement calculation (Income - Expense) vs Goal

    }



    public Map<String, Double> getSpendingByCategory() {
        // TODO: CHALLENGE 4 - Implement groupingBy

        List<Transaction> transactions  = transactionRepository.findAll();
                      
                         Map<String, Double> result = transactions.stream()
                                   
                                      .collect(Collectors.groupingBy(n -> n.categoryName(),Collectors.summingDouble(n -> n.amount())));
        return result;
    }

}