package com.example.budgetmanagement.model;



import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class UserCreation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;

    
}