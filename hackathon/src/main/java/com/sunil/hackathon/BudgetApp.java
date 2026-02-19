package com.sunil.hackathon;
import java.util.List;
import java.util.Scanner;

import com.sunil.hackathon.controller.AppController;
import com.sunil.hackathon.model.Transaction;
import com.sunil.hackathon.repository.TransactionRepository;
import com.sunil.hackathon.service.BudgetService;
public class BudgetApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("=== JAVA 21 INTERN HACKATHON: PERSONAL BUDGET TRACKER ===");

        // CHALLENGE 5: ARCHITECTURAL WIRING
        // TODO: Wire layers together (Repos -> Service -> Controller)
       

        TransactionRepository transactionRepository = new TransactionRepository();

        BudgetService budgetService = new BudgetService(transactionRepository);

        AppController controller = new AppController(budgetService); // CHALLENGE: Wire your controller here!
         
        if (controller == null){

            System.err.println("\n[!] FATAL: Application wiring incomplete.");
            System.exit(1);
            
        }

        while (true) {
            System.out.println("\nMENU: [1] Add Trans | [2] List (Date) | [3] List (Amount) | [4] Stats | [5] Add Cat | [6] Summary | [7] Exit");
            System.out.print("Input: ");
            String choice = sc.nextLine();

            switch (choice) {
                
                case "1" -> {

                    System.out.print("Desc: "); String d = sc.nextLine();
                    System.out.print("Amt: "); String a = sc.nextLine();
                    System.out.print("Cat: "); String c = sc.nextLine();
                    System.out.print("Type: "); String t = sc.nextLine();


                     controller.handleAddTransaction(d,a,c,t);

                    // TODO: Wire controller

                    
                }


                //now working 

                case "2" -> {

                System.out.print("Enter Date : "); String date = sc.nextLine();
                    
                  List<Transaction> transactions = controller.listOfTransactionsByDate(date);

                  System.out.println(transactions);
                    /* TODO: List by date */ }


                case "3" -> { /* TODO: List by amount */ }
                case "4" -> {
                    // TODO: Wire showAdvancedStats()
                }

                case "5" -> {

     
                    System.out.print("Name: "); String n = sc.nextLine();
                    System.out.print("Limit: "); String l = sc.nextLine();

                             

                    // TODO: Wire controller
                }
                case "6" -> { /* TODO: Wire showDashboard() */ }
                case "7" -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }
}