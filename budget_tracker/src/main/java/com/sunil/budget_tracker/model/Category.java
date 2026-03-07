package com.sunil.budget_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
//@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


        private String categoryName;

    private double budgetLimit;

   
    public Category() {
    }

    public Category(String name, double budgetLimit) {
        this.categoryName = name;
        this.budgetLimit = budgetLimit;
    }
 
    public long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getBudgetLimit() {
        return budgetLimit;
    }

    
    public void setId(long id) {
        this.id = id;
    }

    public void setCategoryName(String name) {
        this.categoryName = name;
    }

    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

 @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + categoryName + '\'' +
                ", budgetLimit=" + budgetLimit +
                '}';

}
}