

package com.sunil.budget_tracker.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class UserProfile {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private double monthlySavingsGoal;

    public UserProfile() {
    }

 
    public UserProfile(long id,String username, double monthlySavingsGoal) {
        this.id = id;
        this.username = username;
        this.monthlySavingsGoal = monthlySavingsGoal;
    }

  
    public String getUsername() {
        return username;
    }

    public double getMonthlySavingsGoal() {
        return monthlySavingsGoal;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMonthlySavingsGoal(double monthlySavingsGoal) {
        this.monthlySavingsGoal = monthlySavingsGoal;
    }

 
    public String toCsv() {
        return username + "," + monthlySavingsGoal;
    }


    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", monthlySavingsGoal=" + monthlySavingsGoal +
                '}';
    }


}