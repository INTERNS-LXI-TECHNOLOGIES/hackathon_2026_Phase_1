package com.sunil.budget_tracker.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Transaction implements Comparable<Transaction>{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

  private   long id;
  @CreationTimestamp
  private   LocalDate date;
  private   String description;
  private   double amount;
  private   String categoryName;
  @Enumerated(EnumType.STRING)
  private   TransactionType type;


  public Transaction() {
}


public  Transaction(long id,LocalDate date,String description,double amount,String categoryName,TransactionType type) {

this.id = id;
this.date = date;
this.description = description;
this.amount = amount;
this.categoryName = categoryName;
this.type = type;

}

public void setId(long id){

    this.id = id;

}

public long getId(){

return id;

}


public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type){

      this.type = type;

    }


@Override
public String toString(){
    return "Transaction [id=" + id + 
           ", date=" + date + 
           ", description=" + description + 
           ", amount=" + amount + 
           ", categoryName=" + categoryName + 
           ", type=" + type + "]";
}


public int compareTo(Transaction t){

    return this.date.compareTo(t.getDate());

}


}

 


