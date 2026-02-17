package com.athul.hackathone.model;

public record Category(String name, double budgetLimit) {
    public String toCsv() { return name + "," + budgetLimit; }
}