package com.athul.hackathone.repo;

import com.athul.hackathone.execption.DataPersistenceException;

import java.util.List;

public interface BaseRepository<T> {
    List<T> findAll();
    void save(T entity) throws DataPersistenceException;
}