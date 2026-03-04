package com.example.budgetmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.budgetmanagement.service.CategoryService;

public class CategoryController {
@Autowired
private CategoryService categoryService;

    // TODO: Parse limit, create Category object, save using repository
    public void addCategory(String name, String limitStr) {
    }
}
