package com.example.budgetmanagement.model;

import com.example.budgetmanagement.enumtype.TransactionType;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @CreationTimestamp
  private LocalDate date;
  private String description;
  private double amount;
  private String categoryName;
  @Enumerated(EnumType.STRING)
  private TransactionType type;
  // Many → One (FK here)
  @ManyToOne
  @JoinColumn(name = "userprofile_id")
  private UserProfile userProfile;

  // Many → One (FK here)
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

}
