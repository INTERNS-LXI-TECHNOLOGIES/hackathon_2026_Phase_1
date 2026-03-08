package com.sunil.budget_tracker.service;

import com.sunil.budget_tracker.exception.DataPersistenceException;
import com.sunil.budget_tracker.model.Transaction;
import com.sunil.budget_tracker.model.TransactionType;
import com.sunil.budget_tracker.model.UserProfile;

import java.util.stream.Collector;
import java.util.stream.Collectors;


import java.util.*;
import java.util.function.Predicate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunil.budget_tracker.repository.CategoryRepository;
import com.sunil.budget_tracker.repository.TransactionRepository;
import com.sunil.budget_tracker.repository.UserProfileRepository;

import java.util.List;
import com.sunil.budget_tracker.model.Category;

@Service
public class BudgetService {

@Autowired
private TransactionRepository transactionRepository;

@Autowired
private CategoryRepository categoryRepository;
   

@Autowired
private UserProfileRepository userProfileRepository;

    // TODO: CHALLENGE 5 (Part D/E) - Define repos and implement constructor for wiring

    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        

       List<Transaction> transaction = transactionRepository.findAll();
                                           Map<Boolean,List<Transaction>> income =  transaction.stream()
                                                            .collect(Collectors.partitioningBy(t ->t.getType() == TransactionType.INCOME)); 
        // CHALLENGE 12: PARTITIONING DATA
        // TODO: Implement using .stream().collect(Collectors.partitioningBy(t -> t.type() == TransactionType.INCOME))
        return  income;

    }



    public DoubleSummaryStatistics getExpenseStatistics(){
   
        List<Transaction> transactions  = transactionRepository.findAll();

                                        DoubleSummaryStatistics stat = transactions.stream()
                                                                .filter(n ->n.getType() == TransactionType.EXPENSE)
                                                                .mapToDouble(n ->n.getAmount())
                                                                .summaryStatistics();

        // CHALLENGE 13: STATISTICAL SUMMARY
        // TODO: Implement using .stream().filter(expenses).mapToDouble(t -> t.amount()).summaryStatistics()
        
        return stat;

    }





    // now working 

    public String  hasHighValueTransaction(String  categoryName, double threshold) {

    List<Transaction> transactions =  transactionRepository.findAll();
    
    boolean exists = transactions.stream()
                             .anyMatch(n -> n.getCategory().getCategoryName().equalsIgnoreCase(categoryName));
                          
           
                if(!exists){

                 return "No Category Available";   

                } 
                

                        boolean highValue =    transactions.stream()
                                       .filter(n ->n.getCategory().getCategoryName().equalsIgnoreCase(categoryName))
                                       .anyMatch(n ->n.getAmount() > threshold);

                     if(highValue){

                        return "High value transaction detected";
                     }

         return " All transactions within threshold";

        // CHALLENGE 14: EXISTENCE & THRESHOLDS
        // TODO: Implement using .stream().anyMatch(...)
       
    }





    
    public Optional<Transaction> getHighestExpense(){
     List<Transaction> transactions =   transactionRepository.findAll();
                
                     return   transactions.stream()
                                   .filter(n -> n.getType() == TransactionType.EXPENSE)
                                   
                                   .max(Comparator.comparing(n -> n.getAmount()));
                                   
        // CHALLENGE 15: TOP EXPENSE FINDER
        // TODO: Implement using .stream().filter(expenses).max(Comparator.comparingDouble(...))
        
    }

    public List<String>   getCategoryReport(){
       
       List<Category> category =  categoryRepository.findAll();

                          return  category.stream()
                                          .distinct()
                                          .map(n -> n.getCategoryName())
                                          .toList();

                                           

         // displaying category     
        // CHALLENGE 16: DATA JOINING  i changed the task 
        // TODO: Implement using .stream().map(...).distinct().sorted().collect(Collectors.joining(", "))
      
    }



    public List<Transaction> filterTransactions(Predicate<Transaction> filter) {
        // TODO: CHALLENGE 9 - Implementation needed

          List<Transaction>   transactions =  transactionRepository.findAll();
                              
        return    transactions.stream()
                                         .filter(n -> n.getAmount()> 1000)
                                         .toList();

       
    }



    public Set<String> getUniqueDescriptions() {


     
      List<Transaction> transactions =   transactionRepository.findAll();
                        
                       Set<String> descriptions =  transactions.stream()
                                   
                                    .map(n ->n.getDescription())
                                    .collect(Collectors.toSet());

         

        // TODO: CHALLENGE 10 - Implementation needed
        return descriptions;
    }

    public void addTransaction(Transaction t) {


        transactionRepository.save(t);

        if (t.getType() == TransactionType.EXPENSE) {



            // TODO: CHALLENGE 3 - Implement budget ceiling check
        }
        // TODO: CHALLENGE 5 - Save via transRepo
    }

    public List<Transaction> getTransactionsSortedByAmount() {
               
             List<Transaction> transactions =   transactionRepository.findAll();

           
        return  transactions.stream()     
                         
                         .sorted(Comparator.comparing(n ->n.getAmount()))
                         .toList();


    }

  

    public List<Transaction> fetchAllSortedByDate() {

           List<Transaction> transactions =  transactionRepository.findAll();
                            
                                      Collections.sort(transactions);
                                       
                    
    return transactions;

        // TODO: CHALLENGE 7 - Implement sorting
        
    }

    public String getGoalStatus() {

        List<Transaction> transactions = transactionRepository.findAll();

        List<UserProfile>  user = userProfileRepository.findAll();

                       double monthlySaving =  user.stream()
                                 .mapToDouble(n ->n.getMonthlySavingsGoal())
                                 .sum();

                      double  expense =  transactions.stream()

                                      .filter(n ->n.getType()== TransactionType.EXPENSE)
                                      .collect(Collectors.summingDouble(n -> n.getAmount()));



                        double income =  transactions.stream()

                                      .filter(n ->n.getType() == TransactionType.INCOME)
                                    
                                      .collect(Collectors.summingDouble(n -> n.getAmount()));


                        

                                double  saving =  income - expense ; 

                         

                               if(saving >= monthlySaving){
                    

                                return "Goal Achieved";

                               }else{

                              return "Not Achieved Goal";

                               }



        // TODO: CHALLENGE 2 - Implement calculation (Income - Expense) vs Goal

    }


    //  refactoring 
    public Map<String, Double> getSpendingByCategory() {

        List<Transaction> transactions = transactionRepository.findAll();

         
                    Map<String,Double> groupBy =  transactions.stream()
                                                              .collect(Collectors.groupingBy(n -> n.getCategory().getCategoryName(),Collectors.summingDouble(n -> n.getAmount())));
                    return groupBy;

        // TODO: CHALLENGE 4 - Implement groupingBy
     
    }
}
