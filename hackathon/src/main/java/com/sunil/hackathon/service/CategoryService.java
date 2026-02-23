package com.sunil.hackathon.service;

import com.sunil.hackathon.repository.CategoryRepository;
import com.sunil.hackathon.exception.DataPersistenceException;
import com.sunil.hackathon.model.Category;

public class CategoryService{

private CategoryRepository categoryRepository;

 public CategoryService(CategoryRepository categoryRepository){

this.categoryRepository = categoryRepository ;

 }


 public void addCategoryService(String name, String limitStr) throws DataPersistenceException {

 double limit = Double.parseDouble(limitStr);


    Category c = new Category(name,limit); 


      categoryRepository.save(c);


 }





    
}