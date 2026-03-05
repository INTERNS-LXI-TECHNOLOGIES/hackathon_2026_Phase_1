package com.example.budgetmanagement.controller;

import com.example.budgetmanagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.example.budgetmanagement.service.CategoryService;

@Controller
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/addcategorypage")
    public String addCategory() {
        return "home";
    }

    @GetMapping("/addcategorys")
    public String addcategorypage(Model model) {
        Category c = new Category();
        model.addAttribute("categories", c);
        return "addcategory";
    }

    // TODO: Parse limit, create Category object, save using repository
    @PostMapping("/addcategoryitem")
    public void addCategory(String name, String limitStr) {
        double limit=Double.parseDouble(limitStr);
       // Category c = new Category(name, limit);
    }
}
