package com.sunil.budget_tracker.service;

import com.sunil.budget_tracker.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunil.budget_tracker.repository.CategoryRepository;
@Service
public class CategoryService{

@Autowired
private CategoryRepository categoryRepository;


public void addCategory(Category c){
    
categoryRepository.save(c);


}


}  