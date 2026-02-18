package com.akshay.hackthon.model;

import com.akshay.hackthon.model.*;


public record Category(String name, double budgetLimit) {
        

    public String toCsv() { return name + "," + budgetLimit; }
}

   