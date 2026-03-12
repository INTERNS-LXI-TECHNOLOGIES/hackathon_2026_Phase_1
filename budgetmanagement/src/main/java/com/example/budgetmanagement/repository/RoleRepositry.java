package com.example.budgetmanagement.repository;
import com.example.budgetmanagement.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepositry extends JpaRepository<Role, Long> {
}