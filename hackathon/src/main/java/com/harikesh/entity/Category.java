package com.harikesh.entity;


public record Category(String name, double budgetLimit) {
    public String toCsv() { return name + "," + budgetLimit; }

    public Category(String name, double budgetLimit){
        this.name= name;
        this.budgetLimit= budgetLimit;
    }
}
