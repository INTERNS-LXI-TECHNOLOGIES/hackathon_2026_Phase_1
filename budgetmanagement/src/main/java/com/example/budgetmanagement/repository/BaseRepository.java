package com.example.budgetmanagement.repository;
import com.example.budgetmanagement.exception.*;
import java.util.List;
interface BaseRepository<T> {
    List<T> findAll();
    void save(T entity) throws DataPersistenceException;
}
