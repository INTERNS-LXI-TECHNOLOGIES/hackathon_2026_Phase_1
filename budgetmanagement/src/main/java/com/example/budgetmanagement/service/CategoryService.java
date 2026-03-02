package com.example.budgetmanagement.service;

import java.util.Map;

import com.example.budgetmanagement.model.Category;
import com.example.budgetmanagement.repository.CategoryRepository;

public class CategoryService {

    private final CategoryRepository categoryRepo;

    // TODO: Constructor Injection
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    // TODO: Add category
    public void addCategory(Category category) {
    }

    // TODO: Group spending by category
    public Map<String, Double> getSpendingByCategory() {
        return Map.of();
    }
}