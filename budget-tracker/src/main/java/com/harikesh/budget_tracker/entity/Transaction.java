package com.harikesh.budget_tracker.entity;

import java.time.LocalDateTime;

//import jakarta.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private long id;

    private String description;
    private double amount;
    private String category;
    private String type;

private LocalDateTime date;
    public Transaction() {
}


    public Transaction(String description , double amount, String category, String type){
        this.description = description;
        this.amount= amount;
        this.category = category;
        this.type = type;
        this.date = LocalDateTime.now();
    }


   
    public long getId(){
         return id; 
        }

        public void setId(long id){
             this.id = id; 
            }
    public String getDescription(){
         return description; 
        }

        public void setDescription(String description){
             this.description = description;
            }

    public double getAmount(){
         return amount; 
        }

        public void setAmount(double amount){
             this.amount = amount; 
            }

    public String getCategory(){
         return category; 
        }

    public void setCategory(String category){
         this.category = category; 
        }

        public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
}


public LocalDateTime getDate() {
    return date;
}

public void setDate(LocalDateTime date) {
    this.date = date;
}


}
