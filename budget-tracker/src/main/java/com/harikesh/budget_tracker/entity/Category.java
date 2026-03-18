package com.harikesh.budget_tracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double spendingLimit;

    public Category(){}

        public Category(String name, double spendingLimit){
        this.name = name;
        this.spendingLimit = spendingLimit;
    }

      public Long getId() {
        return id;
    }

     public String getName() {
        return name;
    }

     public double getSpendingLimit() {
        return spendingLimit;
    }

    public void setId(Long id) {
        this.id = id;
    }

      public void setName(String name) {
        this.name = name;
    }

    public void setSpendingLimit(double spendingLimit) {
        this.spendingLimit = spendingLimit;
    }
}