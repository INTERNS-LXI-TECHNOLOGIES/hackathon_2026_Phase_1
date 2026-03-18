package com.akshay.BudgetApp.model;

import java.time.LocalDate;

public record Transaction(
        String id,
        LocalDate date,
        String description,
        double amount,
        String categoryName,
        TransactionType type
) {

    public String toCsv() {

        return id + "," +
               date + "," +
               description + "," +
               amount + "," +
               categoryName + "," +
               type;
    }
}