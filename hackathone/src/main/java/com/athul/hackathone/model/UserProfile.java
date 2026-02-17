package com.athul.hackathone.model;


public record UserProfile(String username, double monthlySavingsGoal) {
    public String toCsv() { return username + "," + monthlySavingsGoal; }
}