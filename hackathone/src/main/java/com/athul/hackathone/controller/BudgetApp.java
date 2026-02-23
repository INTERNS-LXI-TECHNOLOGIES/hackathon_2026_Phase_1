package com.athul.hackathone.controller;

import com.athul.hackathone.repo.Repository.CategoryRepository;
import com.athul.hackathone.repo.Repository.TransactionRepository;
import com.athul.hackathone.repo.Repository.UserProfileRepository;
import com.athul.hackathone.service.BudgetService;

import java.util.Scanner;

public class BudgetApp {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== JAVA 21 INTERN HACKATHON: PERSONAL BUDGET TRACKER ===");

        // CHALLENGE 5: ARCHITECTURAL WIRING
        // TODO: Wire layers together (Repos -> Service -> Controller)

        CategoryRepository categoryRepository= new CategoryRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        BudgetService budgetService = new BudgetService(transactionRepository,userProfileRepository,categoryRepository);
       AppController controller = new AppController(budgetService, categoryRepository); // CHALLENGE: Wire your controller here!

       if (controller == null) {
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
                    System.out.print("Type: "); String t = sc.nextLine().toUpperCase();
                    // TODO: Wire controller
                    controller.handleAddTransaction(d,a,c,t);

                }
                case "2" -> { /* TODO: List by date */
                //System.out.println("Enter date :");  String e = sc.nextLine();
                controller.listTransactions(false);
                }
                case "3" -> { /* TODO: List by amount */
                controller.listTransactions(true );}
                case "4" -> {
                    controller.showAdvancedStats();
                    // TODO: Wire showAdvancedStats()
                }
                case "5" -> {
                    System.out.print("Name: "); String n = sc.nextLine();
                    System.out.print("Limit: "); String l = sc.nextLine();
                    // TODO: Wire controller
                    controller.addCategory(n,l);
                }
                case "6" -> { /* TODO: Wire showDashboard() */
                controller.showDashboard();
                }
                case "7" -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }
}