package com.example.budgetmanagement.repository;
import com.example.budgetmanagement.exception.*;
import com.example.budgetmanagement.model.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction,Long>{

    
}