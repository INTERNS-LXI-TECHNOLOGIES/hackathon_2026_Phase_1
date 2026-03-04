package com.example.budgetmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Id;


@Data
@NoArgsConstructor
@Table(name="category")

@Entity
public class Category {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  Long id;
    String name;
    double budgetLimit;

}
