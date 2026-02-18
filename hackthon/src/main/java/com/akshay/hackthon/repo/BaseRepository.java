package com.akshay.hackthon.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import com.akshay.hackthon.model.TransactionType;
import java.util.*;
import com.akshay.hackthon.repo.BaseRepository;
import com.akshay.hackthon.exception.*;

public interface BaseRepository<T> {
    List<T> findAll();
     public void save(T entity) throws DataPersistenceException;
}

 