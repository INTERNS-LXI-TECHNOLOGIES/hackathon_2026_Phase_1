package com.example.budgetmanagement.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Id;

@Data
@NoArgsConstructor
@Table(name="userprofile")
@Entity
public class  UserProfile{
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  Long id;
    String username;
    double monthlySavingsGoal;
}
