package com.example.budgetmanagement.controller;

import com.example.budgetmanagement.model.*;
import com.example.budgetmanagement.repository.CategoryRepository;

import java.util.UUID;
import java.util.List;
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
    @Autowired
    private CategoryRepository categoryRepository;

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
    public String addCategory(String name, String limitStr) {
        double limit = Double.parseDouble(limitStr);

        Category c = new Category();
        c.setName(name);
        c.setBudgetLimit(limit);
        categoryRepository.save(c);
        return "redirect:/api/category/viewaddcategory";
    }

    @GetMapping("/viewaddcategory")
    public String showAddCategoryPage(Model model) {
        List<Category> c=categoryRepository.findAll();
        model.addAttribute("categories", c);
        return "viewaddcategory";
    }
}
