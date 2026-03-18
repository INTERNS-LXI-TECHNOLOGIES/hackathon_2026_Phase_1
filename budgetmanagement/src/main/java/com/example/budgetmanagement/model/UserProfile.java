package com.example.budgetmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Data
@NoArgsConstructor
@Table(name = "userprofile")
@Entity
public class UserProfile {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String username;
   
   // Owning side (FK here)
   @OneToOne
   @JoinColumn(name = "user_id") // FK
   private User user;

   // One profile → many transactions
   @OneToMany(mappedBy = "userProfile")
   private List<Transaction> transactions;

}
