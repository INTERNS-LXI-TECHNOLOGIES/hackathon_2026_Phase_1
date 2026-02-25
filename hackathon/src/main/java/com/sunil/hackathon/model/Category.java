
 package com.sunil.hackathon.model;

 public record Category(String name, double budgetLimit) {


 public String toCsv() { return name + "," + budgetLimit; }

 public static Category valueOf(String upperCase) {
    
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'valueOf'");


}






}