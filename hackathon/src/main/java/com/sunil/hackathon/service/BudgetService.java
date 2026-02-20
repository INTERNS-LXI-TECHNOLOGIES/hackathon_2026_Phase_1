package com.sunil.hackathon.service;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.stream.*;
import java.util.function.*;

import com.sunil.hackathon.exception.DataPersistenceException;
import com.sunil.hackathon.model.Transaction;
import com.sunil.hackathon.model.TransactionType;
import com.sunil.hackathon.repository.TransactionRepository;

public class BudgetService {

    private TransactionRepository transactionRepository;



    public BudgetService(TransactionRepository transactionRepository){

    this.transactionRepository = transactionRepository;

    }
   



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

        double myBudget = 5000;

        List<Double> listOfTransactions = new ArrayList<>();

        if (t.type() == TransactionType.EXPENSE) {

            
           double transactionsAmounts = t.amount();

           double reducedBudget = myBudget - transactionsAmounts;
       
            listOfTransactions.add(reducedBudget);

           System.out.println("Total Cash in Your Account : " + myBudget );

            System.out.println("Now Your Balance - " + listOfTransactions );

      
            // TODO: CHALLENGE 3 - Implement budget ceiling check
            
        }else{

       double pluseAmount = t.amount();
       
     double  mySavings = myBudget + pluseAmount;

     listOfTransactions.add(mySavings);

     System.out.println("Total Cash in Your Account : " + myBudget );

     System.out.println("Now Your Balance + " + listOfTransactions );

    }if(t.amount() > myBudget){

       throw new DataPersistenceException("Insufficient Money", null);
    }


        transactionRepository.save(t);

        // TODO: CHALLENGE 5 - Save via transRepo
    }

    



    //now working
    public List<Transaction> getTransactionsSortedByAmount(double amount) {


        return  transactionRepository.amountSort(amount);

        // TODO: CHALLENGE 7 - Implement sorting
       

    }




    public List<Transaction> fetchAllSortedByDate(LocalDate date){

        //TODO: CHALLENGE 7 - Implement sorting
      return  transactionRepository.findAll(date);
        
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