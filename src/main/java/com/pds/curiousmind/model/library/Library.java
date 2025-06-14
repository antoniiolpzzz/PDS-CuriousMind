package com.pds.curiousmind.model.library;

import java.util.List;

/**
 * Interface for a generic library that manages objects of type T.
 * @param <T> the type of objects managed by the library
 * @apiNote  This interface defines methods to add, remove, retrieve all objects,
 * and get an object by its ID.
 */
public interface Library<T> {

    T add(T object);
    boolean remove(T object);
    T update(T object);
    List<T> getAll();
    T getById(Long id);

}
