package com.harikesh.entity;
// ============================================================================
// 2. ENTITY LAYER - JAVA 21 RECORDS (Intended: com.{name}.hackathon.model)
// ============================================================================

import java.time.LocalDate;

/**
 * CHALLENGE 7: Implement Comparable for natural sorting by Date (newest first).
 */
public record Transaction(
    String id,
    LocalDate date,
    String description,
    double amount,
    String categoryName,
    TransactionType type
) {
   
   
    public String toCsv() {
        return String.join(",", id, date.toString(), description, String.valueOf(amount), categoryName, type.name());
    }
}
