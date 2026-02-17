package com.athul.hackathone.model;

record Category(String name, double budgetLimit) {
    public String toCsv() { return name + "," + budgetLimit; }
}
 