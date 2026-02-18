package com.sunil.hackathon.model;
import java.time.LocalDate;
public record Transaction(
    String id,
    LocalDate date,
    String description,
    double amount,
    String categoryName,
    TransactionType type
    
) {
    // TODO: CHALLENGE 7 - Add Comparable implementation here
   
    public String toCsv() {
        return String.join(",", id, date.toString(), description, String.valueOf(amount), categoryName, type.name());
    }
}


