package com.sunil.budget_tracker.repository;

import java.util.List;

import com.sunil.budget_tracker.exception.DataPersistenceException;

public interface BaseRepository<T> {
    List<T> findAll();


    void save(T entity) throws DataPersistenceException;
    
}