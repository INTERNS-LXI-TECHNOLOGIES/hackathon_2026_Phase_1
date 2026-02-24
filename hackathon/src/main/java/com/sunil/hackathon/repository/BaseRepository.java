package com.sunil.hackathon.repository;
import java.util.List;
import com.sunil.hackathon.model.Transaction;

import com.sunil.hackathon.exception.DataPersistenceException;

interface BaseRepository<T> {


    List<T> findAll();
    void save(T entity) throws DataPersistenceException;





}