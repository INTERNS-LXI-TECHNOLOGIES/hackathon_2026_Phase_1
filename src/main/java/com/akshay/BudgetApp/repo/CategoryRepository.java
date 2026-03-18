package com.akshay.BudgetApp.repo;

import com.akshay.BudgetApp.model.Category;

import tools.jackson.databind.DatabindException;

import java.nio.file.*;
import java.util.List;

public class CategoryRepository implements BaseRepository<Category> {

    private final Path path = Paths.get("data_categories.csv");

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void save(Category entity) throws DatabindException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
