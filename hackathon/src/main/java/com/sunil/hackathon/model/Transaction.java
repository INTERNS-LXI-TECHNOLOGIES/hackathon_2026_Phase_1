package com.sunil.hackathon.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.lang.Comparable;

public record Transaction(

    String id,
    LocalDate date,
    String description,
    double amount,
    String categoryName,
    TransactionType type
    
) implements Comparable<Transaction>{


    // TODO: CHALLENGE 7 - Add Comparable implementation here
   

    public String toCsv() {
        return String.join(",", id, date.toString(), description, String.valueOf(amount),categoryName, type.name());
    }

    
   

    @Override
    public int compareTo(Transaction t){
    return this.date().compareTo(t.date());
    }
    
}


