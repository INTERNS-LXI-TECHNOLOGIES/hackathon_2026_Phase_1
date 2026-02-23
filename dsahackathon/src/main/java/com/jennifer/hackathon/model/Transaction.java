package com.jennifer.hackathon.model;
import java.time.LocalDate;
import com.jennifer.hackathon.enumType.*;
public record Transaction (
    String id,
    LocalDate date,
    String description,
    double amount,
    String categoryName,
    TransactionType type
)


{public String toCsv() {
        return String.join(",", id, date.toString(), description, String.valueOf(amount), categoryName, type.name());
    }

}
