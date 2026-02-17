package com.athul.hackathone.model;


record UserProfile(String username, double monthlySavingsGoal) {
    public String toCsv() { return username + "," + monthlySavingsGoal; }
}