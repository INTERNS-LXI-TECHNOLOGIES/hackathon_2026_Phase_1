package com.harikesh.controller;
// ============================================================================
// 5. CONTROLLER LAYER (Intended: com.{name}.hackathon.controller)
// ============================================================================

public class AppController {
   
    

    public void handleAddTransaction(String desc, String amtStr, String cat, String typeStr) {
        try {
           
            System.out.println("Transaction recorded.");
        } catch (Exception e) {
            System.err.println("Controller Error: " + e.getMessage());
        }
    }

    public void addCategory(String name, String limitStr) {
        try {
            
            System.out.println("Category added.");
        } catch (Exception e) {
            System.err.println("Category Error: " + e.getMessage());
        }
    }

   

    public void showAdvancedStats() {
        System.out.println("\n--- ADVANCED FINANCIAL INSIGHTS ---");
       
        System.out.println("------------------------------------");
    }

    public void showDashboard() {
        System.out.println("\n--- BUDGET DASHBOARD ---");
       
        System.out.println("-------------------------");
    }

    public void fetchAllSortedByDate() {
        System.out.println("date");
    }
}