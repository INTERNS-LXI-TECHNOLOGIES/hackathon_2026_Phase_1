package com.akshay.BudgetApp.repo;

import java.util.List;

public interface BaseRepository<T> {

    List<T> findAll();

    void save(T entity) throws Exception;

}