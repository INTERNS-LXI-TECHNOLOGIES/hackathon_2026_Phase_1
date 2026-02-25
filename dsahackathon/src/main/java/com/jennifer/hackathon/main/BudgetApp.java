package com.jennifer.hackathon.main;

import java.time.LocalDate;
import java.util.Scanner;
import com.jennifer.hackathon.controller.AppController;
import com.jennifer.hackathon.enumType.TransactionType;
import com.jennifer.hackathon.repositry.CategoryRepository;
import com.jennifer.hackathon.repositry.TransactionRepository;
import com.jennifer.hackathon.repositry.UserProfileRepository;
import com.jennifer.hackathon.service.*;

public class BudgetApp {
    private static final Scanner sc = new Scanner(System.in);
    private static TransactionRepository transactionrepo = new TransactionRepository();
    private static CategoryRepository categoryrepo = new CategoryRepository();
    private static UserProfileRepository userProfilerepo = new UserProfileRepository();
    private static BudgetService budgetService = new BudgetService(transactionrepo, categoryrepo, userProfilerepo);

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
                    "\nMENU: [1] Add Trans | [2] List (Date) | [3] List (Amount) | [4] Stats | [5] Add Cat | [6] Summary | [7]Check High Transcation | [8]Filter by types |  [9]Create User  | [10]Savings Goal  [11]Description  |  [12] Exit");
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

                    System.out.println("1. First Transcation to Last");
                    System.out.println("2. Last Transcation to First");
                    String input = sc.nextLine();

                    boolean transactionsByDate = input.equals("1");

                    controller.listTransactionsByDate(transactionsByDate);

                    /* TODO: List by date */ }
                case "3" -> {

                    System.out.println("1. small Amount to Large");
                    System.out.println("2. Large Amount to small");
                    String input = sc.nextLine();

                    boolean transactionsByAmount = input.equals("1");

                    controller.listTransactionsByAmount(transactionsByAmount);
                    /* TODO: List by amount */ }
                case "4" -> {
                    controller.showAdvancedStats();
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
                    controller.showDashboard();
                    /* TODO: Wire showDashboard() */ }
                case "7" -> {
                    System.out.println("Enter the Category: ");
                    String category = sc.nextLine();
                    System.out.println("Enter the thereshold Amount: ");
                    String a = sc.nextLine();
                    double amount = Double.parseDouble(a);
                    controller.highValueTranscation(category, amount);
                }
                case "8" -> {
                    System.out.println("1.Filter by Date: ");
                    System.out.println("2.Filter by TranscationType: ");
                    System.out.println("3.Filter by Category: ");
                    System.out.println("4.Filter by Amount: ");
                    System.out.println("Enter Your Choice: ");
                    String filterByChoice = sc.nextLine();

                    switch (filterByChoice) {
                        case "1" -> {
                            System.out.println("From date:");
                            String d = sc.nextLine();
                            LocalDate fromdate = LocalDate.parse(d);
                            System.out.println("To date:");
                            String tod = sc.nextLine();
                            LocalDate toDate = LocalDate.parse(tod);
                            controller.filteringTranscationsDetails(
                                    t -> !t.date().isBefore(fromdate) && !t.date().isAfter(toDate));
                        }
                        case "2" -> {
                            System.out.println("Enter Transcation Type:(INCOME/EXPENSE)");
                            String type = sc.nextLine();
                            controller.filteringTranscationsDetails(t -> t.type().name().equalsIgnoreCase(type));

                        }

                        case "3" -> {
                            System.out.println("Enter Category : ");
                            String category = sc.nextLine();
                            controller.filteringTranscationsDetails(c -> c.categoryName().equals(category));

                        }

                        case "4" -> {
                            System.out.println("Enter Amount: ");
                            String am = sc.nextLine();
                            double amount = Double.parseDouble(am);
                            controller.filteringTranscationsDetails(a -> a.amount() >= amount);
                        }
                    }

                }
                case "9" -> {
                    System.out.println("Enter name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Monthly saving goal: ");
                    String ss = sc.nextLine();
                    double monthlySavingAmount = Double.parseDouble(ss);
                    controller.user(name, monthlySavingAmount);
                }

                case "10" ->{
                    controller.showDashboard();
                }
                case "11" ->{
                    controller.showDashboard();
                }
                case "12" -> System.exit(0);

                default -> System.out.println("Invalid option.");
            }
        }
    }
}