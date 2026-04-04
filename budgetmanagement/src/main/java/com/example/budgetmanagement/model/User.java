package com.example.budgetmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Set;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
@Entity

@Data
@NoArgsConstructor
public class User {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String userName;
    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_roles",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id")
)
private Set<Role> roles;
}
