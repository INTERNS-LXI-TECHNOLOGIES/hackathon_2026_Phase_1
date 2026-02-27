package com.athul.hackathone.controller;
import com.athul.hackathone.model.Category;
import com.athul.hackathone.model.Transaction;
import com.athul.hackathone.model.TransactionType;
import com.athul.hackathone.repo.Repository.CategoryRepository;
import com.athul.hackathone.service.BudgetService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class AppController {
   
    // TODO: CHALLENGE 5 (Part A/B) - Define and wire Service & Category Repo
   BudgetService service ;
   CategoryRepository catRepo;
   public  AppController(BudgetService service, CategoryRepository catRepo){
       this.service=service;
       this.catRepo = catRepo;
   }

    public void handleAddTransaction(String desc, String amtStr, String cat, String typeStr) {
        try {
            LocalDate dta= LocalDate.now();
            String id = String.valueOf(UUID.randomUUID());
            Double amtDou = Double.valueOf(amtStr);
             TransactionType type =TransactionType.valueOf(typeStr);
            // TODO: Parse inputs and call service.addTransaction
            Transaction transaction = new Transaction(id,dta,desc,amtDou,cat,type);
            service.addTransaction(transaction);
            System.out.println("Transaction recorded.");
        } catch (Exception e) {
            System.err.println("Controller Error: " + e.getMessage());
        }
    }

    public void addCategory(String name, String limitStr) {
       Double limt = Double.valueOf(limitStr);
        Category category = new Category(name ,limt);
        try {
            //TODO: CHALLENGE 5 (Part C) - Wire call to catRepo.save()
              catRepo.save(category);
            System.out.println("Category added.");
        } catch (Exception e) {
            System.err.println("Category Error: " + e.getMessage());
        }
    }

    public void listTransactions(boolean sortByAmount) {
        // TODO: CHALLENGE 7 - Wire service calls for sorting

        if(sortByAmount == true ) {
            List<Transaction> list = service.getTransactionsSortedByAmount();
            for (Transaction x : list){
                System.out.printf("""
                        Id     :%s
                        Date   :%tF
                        Descr  :%s
                        Amount :%.2f
                        Cat Name:%s
                        Type   :%s \n
                        ----------------
                        """,x.id(),x.date(),x.description(),x.amount(),x.categoryName(),x.type());
            }

        } else {
          List<Transaction> list = service.fetchAllSortedByDate();
            for (Transaction x : list){
                System.out.printf("""
                        Date   :%tF
                        Id     :%s
                        Descr  :%s
                        Amount :%.2f
                        Cat Name:%s
                        Type   :%s \n
                        ----------------
                        """,x.date(),x.id(),x.description(),x.amount(),x.categoryName(),x.type());
            }
        }

    }

    public void showAdvancedStats() {
        System.out.println("\n--- ADVANCED FINANCIAL INSIGHTS ---");
        // TODO: CHALLENGE 13 & 15 & 16 - Call service methods and display results
      var  statistics =  service.getExpenseStatistics();

       System.out.println("Max "+statistics.getMax());
       System.out.println("Min "+ statistics.getMin());
       System.out.println("Avg "+ statistics.getAverage());
       System.out.println("Count "+statistics.getCount());
       System.out.println("Sum "+ statistics.getSum());
       System.out.println("Highest Exp " +service.getHighestExpense());
        var list = service.getUniqueDescriptions();
        for (String  i : list){
            System.out.println("Unique Desc "+i);
        }
       System.out.println("Categorys: "+ service.getCategoryReport());

       System.out.println("------------------------------------");
    }

    public void showDashboard() {
        System.out.println("\n--- BUDGET DASHBOARD ---");
        // TODO: CHALLENGE 4 & 12 - Integrate summary and partitioning count
        System.out.println("Showing spent by Category "+service.getSpendingByCategory());
        Map<Boolean ,List<Transaction>> partitioning =  service.getPartitionedTransactions();

        partitioning.forEach((l ,c ) ->
        System.out.println(l + " : "+ c +"\n"));

         System.out.println(service.getGoalStatus());

        System.out.println("-------------------------");
    }



    public void filterUsingCat(String cat) {
        Predicate<Transaction> filter =
                n -> n.categoryName().equalsIgnoreCase(cat.trim());
        var list= service.filterTransactions(filter);
        System.out.println(list);

    }
    public void filterUsingDate(String date) {

       LocalDate da = LocalDate.parse(date);
       //LocalDate ne = da.plusDays(1);

        Predicate<Transaction> filter =
                n -> n.date().equals(date.trim());
        var list= service.filterTransactions(filter);
        System.out.println(list);

    }
}