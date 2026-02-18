package com.jennifer.hackathon.model;

public record Category(String name, double budgetLimit) {
    public String toCsv() {
        return name + "," + budgetLimit;
    }
}