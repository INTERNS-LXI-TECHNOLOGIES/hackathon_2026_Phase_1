package com.sunil.budget_tracker.service;

import com.sunil.budget_tracker.model.Category;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunil.budget_tracker.repository.CategoryRepository;
@Service
public class CategoryService{//singleton desgn pattern

@Autowired
private CategoryRepository categoryRepository;


public void addCategory(Category c){
    
categoryRepository.save(c);


//Category.builder().firstname(sjhbjhb).builfd()
//Category.builder().lastname(sjhbjhb).builfd()

}

public Optional<Category> findById(long id){
 return  categoryRepository.findById(id); 

}



}  