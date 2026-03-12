package com.example.budgetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.budgetmanagement.model.UserCreation;

public interface  UserCreationRepository extends JpaRepository<UserCreation,Long>{
        Optional<UserCreation> findByUsername(String username);

}
