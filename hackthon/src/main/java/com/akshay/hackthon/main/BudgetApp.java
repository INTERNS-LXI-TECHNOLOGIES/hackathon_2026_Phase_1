package com.akshay.hackthon.main;
import java.util.Scanner;
import com.akshay.hackthon.controller.AppController;
import com.akshay.hackthon.repo.TransactionRepository;
import com.akshay.hackthon.service.BudgetService;



public class BudgetApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== JAVA 21 INTERN HACKATHON: PERSONAL BUDGET TRACKER ===");

        // CHALLENGE 5: ARCHITECTURAL WIRING
        // TODO: Wire layers together (Repos -> Service -> Controller)
        
           TransactionRepository transactionRepository = new TransactionRepository();

           BudgetService budgetService = new BudgetService(transactionRepository);

        AppController controller = new AppController(budgetService);

        
        


        
             // CHALLENGE: Wire your controller here!

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
                    // TODO: Wire controller

                    controller.handleAddTransaction(d, a, c, t);
                }
                case "2" -> { /* TODO: List by date */  controller.listTransactions(false); }
                case "3" -> { /* TODO: List by amount */ controller.listTransactions(true); }
                case "4" -> {
                    // TODO: Wire showAdvancedStats()

                    controller.showAdvancedStats();
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