package com.example.budgetmanagement.model;
import com.example.budgetmanagement.enumtype.TransactionType;
import java.time.LocalDate;

public record Transaction (
    String id,
    LocalDate date,
    String description,
    double amount,
    String categoryName,
    TransactionType type
){


public String toCsv() {
        return String.join(",", id, date.toString(), description, String.valueOf(amount), categoryName, type.name());
    }

}