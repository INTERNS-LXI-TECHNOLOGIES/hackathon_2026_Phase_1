package com.harikesh.controller;
// ============================================================================
// 2. ENTITY LAYER - JAVA 21 RECORDS (Intended: com.{name}.hackathon.model)
// ============================================================================

import java.time.LocalDate;

//enum TransactionType { INCOME, EXPENSE }

/**
 * CHALLENGE 7: Implement Comparable for natural sorting by Date (newest first).
 */
public record Transaction(
    String id,
    LocalDate date,
    String description,
    double amount,
    String categoryName,
    //TransactionType type
) {
    
   
    public String toCsv() {
        return String.join(",", id, date.toString(), description, String.valueOf(amount), categoryName, type.name());
    }
}

// record Category(String name, double budgetLimit) {
//     public String toCsv() { return name + "," + budgetLimit; }
// }

// record UserProfile(String username, double monthlySavingsGoal) {
//     public String toCsv() { return username + "," + monthlySavingsGoal; }
// }