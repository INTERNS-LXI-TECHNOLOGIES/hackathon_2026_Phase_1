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
import com.sunil.hackathon.model.UserProfile;
import com.sunil.hackathon.repository.CategoryRepository;
import com.sunil.hackathon.repository.TransactionRepository;
import com.sunil.hackathon.repository.UserProfileRepository;
import com.sunil.hackathon.model.Category;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BudgetService {

     private static final Scanner sc = new Scanner(System.in);


    private TransactionRepository transactionRepository;
    private CategoryRepository categoryRepository;
    private UserProfileRepository userProfileRepository;

    public BudgetService(TransactionRepository transactionRepository, CategoryRepository categoryRepository,
        UserProfileRepository userProfileRepository) {

        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userProfileRepository = userProfileRepository;

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



    public Boolean hasHighValueTransaction(String  category, double threshold) {
    List<Transaction> transactions = transactionRepository.findAll();

    boolean categoryExists = transactions.stream()
            .anyMatch(t -> category.equalsIgnoreCase(t.categoryName()));

    if (!categoryExists) {
        return null;  
    }

    return transactions.stream()
            .filter(t -> category.equalsIgnoreCase(t.categoryName()))
            .anyMatch(t -> t.amount() > threshold);

    
    }



    public Optional<Transaction> getHighestExpense(){

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








    public List<Transaction> filterTransactions() {


       while(true){

            System.out.println("\nMENU: [1] Filter By Date | [2] Filter By Amount | [3] Filter By Expence | [4] Filter By Incom  [5] Exit");
            System.out.print("Input: ");
            String choice = sc.nextLine();


        switch (choice) {
        
        case "1" -> {

        System.out.print("Filter By Date : ");String input  = sc.nextLine();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(input, formatter);


       
       List<Transaction> transactions =  transactionRepository.findAll();
                      return  transactions.stream()
                                    .filter(n -> n.date().equals(date))
                                    .collect(Collectors.toList());

                                   

        }

        case "2" -> {

        System.out.print("Filter By Amount : ");String input  = sc.nextLine();    

         double amount = Double.parseDouble(input);


       List<Transaction> transactions =  transactionRepository.findAll();
                            
                      return  transactions.stream()
                                      .filter(n -> n.amount()== (amount))
                                      .collect(Collectors.toList());

        }

        case "3" ->{
       

                 
            List<Transaction> transactions = transactionRepository.findAll();
                             return  transactions.stream()
                                           .filter(n ->n.type() == TransactionType.EXPENSE)
                                           .collect(Collectors.toList());   
        }

            
       
               case "4" ->{
      
                 
            List<Transaction> transactions = transactionRepository.findAll();
                            return  transactions.stream()
                                           .filter(n ->n.type() == TransactionType.INCOME)
                                           .collect(Collectors.toList());   
        }


        
        case "5" -> System.exit(0);
        default -> System.out.println("Invalid option.");
        
        
       } 



        // TODO: CHALLENGE 9 - Implementation needed

        return new ArrayList<>();


       }



        

    }




    public Set<String> getUniqueDescriptions() {

       List<Transaction>  transactions =  transactionRepository.findAll();
                           
                     Set<String> unique =   transactions.stream()
                                                        .map(n -> n.description())
                                                        .collect(Collectors.toSet());

                         

        // TODO: CHALLENGE 10 - Implementation needed

        return unique;
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

    public List<Transaction> getTransactionsSortedByAmount(){

        List<Transaction>  transactionsByAmount =  transactionRepository.amountSort();
       return  transactionsByAmount.stream()
                                   .sorted((t1,t2) -> Double.compare(t1.amount(), t2.amount()))
                                   .collect(Collectors.toList()); 
        // TODO: CHALLENGE 7 - Implement sorting

    }


    public List<Transaction> fetchAllSortedByDate(){


        // TODO: CHALLENGE 7 - Implement sorting

         List<Transaction> transactionByDate =  transactionRepository.findAllDate();
                      
         return  transactionByDate.stream()
                                .sorted()
                                .collect(Collectors.toList());

                                
    }



    public String getGoalStatus(){

    List<Transaction> transactions =  transactionRepository.findAll();
    
    List<Transaction> expence = transactions.stream()
                                            .filter(n -> n.type()== TransactionType.EXPENSE)
                                            .collect(Collectors.toList());


    List<Transaction> income = transactions.stream()
                                           .filter(n ->n.type()==TransactionType.INCOME)
                                           .collect(Collectors.toList());
                                           
                                           
                    double   calculatedIncome =  income.stream().mapToDouble(n ->n.amount()).sum();
                    double   calculateExpence = expence.stream().mapToDouble(n ->n.amount()).sum();
                    
                    

                    double  savings = calculatedIncome - calculateExpence;

       UserProfile userProfile =  userProfileRepository.load();
       double monthlyGoal =  userProfile.monthlySavingsGoal();


           String status;

           if(savings > monthlyGoal){

            
        status = "Goal Achived";
                

           }else{

            status = "Goal Not Achived";            
           }
      
               return "Income: " + calculatedIncome +
           " Expense:  " + calculateExpence +
           "| Savings: " + savings +
           "| Goal :  " + monthlyGoal + 
           "|Status : " + status;

 

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