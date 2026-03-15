

package com.sunil.budget_tracker.model;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity

public class UserProfile {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String profilePicture;
    private double monthlySavingsGoal;


@OneToOne
@JoinColumn (name = "user_id")
private Users user;

@OneToMany(mappedBy = "userProfileId")

private List<Transaction> transaction;


    public UserProfile() {




    }


 
    public UserProfile(long id,String username,String profilePicture, double monthlySavingsGoal,Users user) {
        this.id = id;
        this.username = username;
        this.profilePicture = profilePicture;
        this.monthlySavingsGoal = monthlySavingsGoal;

        this.user =user;
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

public String getProfilePicture() {
    return profilePicture;
}

public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
}
     

public long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public Users getUser() {
    return user;
}

public void setUser(Users user) {
    this.user = user;
}



public List<Transaction> getTransaction() {
    return transaction;
}


public void setTransaction(List<Transaction> transaction) {
    this.transaction = transaction;
}



@Override
public String toString() {
    return "UserProfile{" +
            "username='" + username + '\'' +
            ", profilePicture='" + profilePicture + '\'' +
            ", monthlySavingsGoal=" + monthlySavingsGoal +
            '}';
}



}