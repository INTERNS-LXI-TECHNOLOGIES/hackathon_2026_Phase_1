package com.akshay.hackathon.repository;

import java.util.List;

import com.akshay.hackathon.exception.DataPersistenceException;

interface BaseRepository<T> {
    List<T> findAll();
    void save(T entity) throws DataPersistenceException;
}
