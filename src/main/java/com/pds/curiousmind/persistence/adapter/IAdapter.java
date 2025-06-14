package com.pds.curiousmind.persistence.adapter;

import java.util.List;

public interface IAdapter<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(Long id);
    List<T> findAll();
}