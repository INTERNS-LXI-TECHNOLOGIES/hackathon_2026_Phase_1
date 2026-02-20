package com.jennifer.hackathon.main;

import java.util.Scanner;
import com.jennifer.hackathon.controller.AppController;
import com.jennifer.hackathon.repositry.CategoryRepository;
import com.jennifer.hackathon.repositry.TransactionRepository;
import com.jennifer.hackathon.service.*;

public class BudgetApp {
    private static final Scanner sc = new Scanner(System.in);
    private static TransactionRepository transactionrepo = new TransactionRepository();
    private static CategoryRepository categoryrepo = new CategoryRepository();
    private static BudgetService budgetService = new BudgetService(transactionrepo, categoryrepo);

    public static void main(String[] args) {
        System.out.println("=== JAVA 21 INTERN HACKATHON: PERSONAL BUDGET TRACKER ===");

        // CHALLENGE 5: ARCHITECTURAL WIRING
        // TODO: Wire layers together (Repos -> Service -> Controller)

        AppController controller = new AppController(transactionrepo, categoryrepo, budgetService); // CHALLENGE: Wire
                                                                                                    // your controller
                                                                                                    // here!

        if (controller == null) {
            System.err.println("\n[!] FATAL: Application wiring incomplete.");
            System.exit(1);
        }

        while (true) {
            System.out.println(
                    "\nMENU: [1] Add Trans | [2] List (Date) | [3] List (Amount) | [4] Stats | [5] Add Cat | [6] Summary | [7] Exit");
            System.out.print("Input: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Desc: ");
                    String d = sc.nextLine();
                    System.out.print("Amt: ");
                    String a = sc.nextLine();
                    System.out.print("Cat: ");
                    String c = sc.nextLine();
                    System.out.print("Type: ");
                    String t = sc.nextLine();
                    controller.handleAddTransaction(d, a, c, t);

                    // TODO: Wire controller
                }
                case "2" -> {
                    System.out.println("Enter date to See list of trancations: ");
                    String date = sc.nextLine();
                    controller.listTransactionsByDate(true);

                    /* TODO: List by date */ }
                case "3" -> {
                    /* TODO: List by amount */ }
                case "4" -> {
                    // TODO: Wire showAdvancedStats()
                }
                case "5" -> {
                    System.out.print("Name: ");
                    String n = sc.nextLine();
                    System.out.print("Limit: ");
                    String l = sc.nextLine();
                    controller.addCategory(n, l);
                    // TODO: Wire controller
                }
                case "6" -> {
                    /* TODO: Wire showDashboard() */ }
                case "7" -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }
}