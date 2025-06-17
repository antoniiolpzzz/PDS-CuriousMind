package com.pds.curiousmind.persistence.adapter;

import java.util.List;
import java.util.Optional;

public interface IAdapter<T> {
    T save(T entity);
    T update(T entity);
    boolean delete(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
}