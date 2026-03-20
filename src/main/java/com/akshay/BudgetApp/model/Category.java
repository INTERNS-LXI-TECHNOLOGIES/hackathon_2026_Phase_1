package com.akshay.BudgetApp.model;

public record Category(String name, double budgetLimit) {


    public String toCsv () {
        return name + "," + budgetLimit ;
    }


    @Override
    public String toString () {
        return name +"" + budgetLimit;
    }
    
}
