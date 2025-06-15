package com.pds.curiousmind.persistence.adapter;

import java.util.List;

public interface IAdapter<T> {
    T save(T entity);
    T update(T entity);
    boolean delete(T entity);
    T findById(Long id);
    List<T> findAll();
}