package com.harikesh.budget_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harikesh.budget_tracker.entity.Category;
import com.harikesh.budget_tracker.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}