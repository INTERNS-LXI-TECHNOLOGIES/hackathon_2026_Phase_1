package com.harikesh.main;
// ============================================================================
// 6. MAIN APP / RUNNER
// ============================================================================

import java.util.Scanner;

import com.harikesh.controller.AppController;

public class BudgetApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== JAVA 21 INTERN HACKATHON: PERSONAL BUDGET TRACKER ===");

        // CHALLENGE 5: ARCHITECTURAL WIRING
        
       
        AppController controller = new AppController(); // CHALLENGE: Wire your controller here!

//        if (controller == null) {
//            System.err.println("\n[!] FATAL: Application wiring incomplete.");
//            System.exit(1);
//        }

        while (true) {
            System.out.println("\nMENU: [1] Add Trans | [2] List (Date) | [3] List (Amount) | [4] Stats | [5] Add Cat | [6] Summary | [7] Exit");
            System.out.print("Input: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Desc: ");
                     String desc = sc.nextLine();
                    System.out.print("Amt: "); 
                    String amt = sc.nextLine();
                    System.out.print("Cat: "); 
                    String cat = sc.nextLine();
                    System.out.print("Type: "); 
                    String type= sc.nextLine();
                    //System.out.println(" Wire controller");
                    controller.handleAddTransaction(desc, amt, cat, type);
                }
                
                
                case "2" ->  controller.fetchAllSortedByDate();
                case "3" -> {System.out.println("amount");}
                case "4" -> {
                    //System.out.println("Wire showAdvancedStats()");
                    controller.showAdvancedStats();
                }
                case "5" -> {
                    System.out.print("Name: "); String n = sc.nextLine();
                    System.out.print("Limit: "); String l = sc.nextLine();
                    
                }
                case "6" -> {System.out.println("Wire showDashboard() ");}
                case "7" -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }


            
        }
    }
}
