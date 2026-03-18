package com.harikesh.budget_tracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.harikesh.budget_tracker.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
