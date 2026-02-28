package com.sunil.budget_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double budgetLimit;

   
    public Category() {
    }

    public Category(String name, double budgetLimit) {
        this.name = name;
        this.budgetLimit = budgetLimit;
    }
 
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBudgetLimit() {
        return budgetLimit;
    }

    
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

 @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budgetLimit=" + budgetLimit +
                '}';

}
}