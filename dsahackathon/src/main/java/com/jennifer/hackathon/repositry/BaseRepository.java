package com.jennifer.hackathon.repositry;
import java.util.List;
import com.jennifer.hackathon.exceptions.DataPersistenceException;


interface BaseRepository<T> {
    List<T> findAll();
    void save(T entity) throws DataPersistenceException;
}