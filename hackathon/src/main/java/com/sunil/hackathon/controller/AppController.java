package com.sunil.hackathon.controller;
import com.sunil.hackathon.service.BudgetService;
import com.sunil.hackathon.service.CategoryService;
import com.sunil.hackathon.model.TransactionType;
import com.sunil.hackathon.repository.CategoryRepository;
import com.sunil.hackathon.model.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class AppController {
   
       

    private BudgetService budgetService;
    private CategoryRepository categoryRepository;
    private CategoryService  categoryService;

    public Object addCategory;

 
    public  AppController(BudgetService bService,CategoryRepository categoryRepository,CategoryService categoryService){
  
    this.budgetService = bService;
    this.categoryRepository = categoryRepository; 
    this.categoryService = categoryService;


   }


    // TODO: CHALLENGE 5 (Part A/B) - Define and wire Service & Category Repo

    public void handleAddTransaction(String desc, String amtStr, String cat, String typeStr) {
        try {

            // TODO: Parse inputs and call service.addTransaction

              Double amount = Double.parseDouble(amtStr);
              TransactionType type = TransactionType.valueOf(typeStr);

           UUID id = UUID.randomUUID();
           String value = String.valueOf(id);

            LocalDate  nowTime = LocalDate.now(); 

             Transaction t = new Transaction(value,nowTime,desc,amount,cat,type);
            
             budgetService.addTransaction(t);


            System.out.println("Transaction recorded.");
        } catch (Exception e) {
            System.err.println("Controller Error: " + e.getMessage());
        }
    }



    
    public void addCategory(String name, String limitStr) {
        try {

              

            // TODO: CHALLENGE 5 (Part C) - Wire call to catRepo.save()
        
                
            categoryService.addCategoryService(name,limitStr);
            
           

            System.out.println("Category added.");
        } catch (Exception e) {
            System.err.println("Category Error: " + e.getMessage());
        }
    }

 

   public  List<Transaction> listOfTransactionsByDate(){

   //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  // LocalDate mydate = LocalDate.parse(date, formatter);


    return budgetService.fetchAllSortedByDate();

    }


  

// now woking 


    public List<Transaction> listTransactions() {
 
            return  budgetService.getTransactionsSortedByAmount();
        // TODO: CHALLENGE 7 - Wire service calls for sorting
        

    }





    public String showAdvancedStats(String name) {

        
        System.out.println("\n--- ADVANCED FINANCIAL INSIGHTS ---");
        // TODO: CHALLENGE 13 & 15 & 16 - Call service methods and display results
        System.out.println("------------------------------------");

       return   budgetService.getGoalStatus(name);



    }

    public void showDashboard() {
        System.out.println("\n--- BUDGET DASHBOARD ---");
        // TODO: CHALLENGE 4 & 12 - Integrate summary and partitioning count
        System.out.println("-------------------------");
    }
}
