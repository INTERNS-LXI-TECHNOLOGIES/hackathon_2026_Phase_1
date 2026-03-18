package com.harikesh.budget_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harikesh.budget_tracker.entity.Transaction;

@Repository
public interface BudgetRepository extends JpaRepository<Transaction, Long> {
    // @Override
    // default List<Transaction> findAll() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    // }
    // @Override
    // default Optional<Transaction> findById(Long id) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'findById'");
    // }
    // @Override
    // default Page<Transaction> findAll(Pageable pageable) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    // }

}
