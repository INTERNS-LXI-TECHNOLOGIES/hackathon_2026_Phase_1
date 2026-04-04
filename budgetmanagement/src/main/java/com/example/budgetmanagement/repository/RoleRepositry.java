package com.example.budgetmanagement.repository;
import com.example.budgetmanagement.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepositry extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}