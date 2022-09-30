package com.my.db.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<T extends Serializable> {
    T get(int id);
    List<T> getAll();
    void save(T entity);
    void delete(T entity);
    void delete(int id);
}
