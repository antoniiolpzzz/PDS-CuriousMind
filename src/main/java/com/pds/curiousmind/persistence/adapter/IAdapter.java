package com.pds.curiousmind.persistence.adapter;

import java.util.List;
import java.util.Optional;

/**
 * Generic adapter interface for basic CRUD operations in the persistence layer.
 * <p>
 * This interface defines standard methods for saving, updating, deleting, and retrieving entities
 * from a data source. It is intended to be implemented by adapter classes that interact with
 * various persistence mechanisms (e.g., databases, file systems).
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     IAdapter<User> userAdapter = new UserAdapter();
 *     User savedUser = userAdapter.save(new User(...));
 *     Optional<User> user = userAdapter.findById(1L);
 *     List<User> allUsers = userAdapter.findAll();
 * </pre>
 * </p>
 *
 * @param <T> the type of entity managed by the adapter
 */
public interface IAdapter<T> {
    /**
     * Saves a new entity to the data source.
     *
     * @param entity the entity to save
     * @return the saved entity (may include generated fields such as ID)
     */
    T save(T entity);

    /**
     * Updates an existing entity in the data source.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Deletes an entity from the data source.
     *
     * @param entity the entity to delete
     * @return true if the entity was deleted successfully, false otherwise
     */
    boolean delete(T entity);

    /**
     * Finds an entity by its unique identifier.
     *
     * @param id the unique identifier of the entity
     * @return an {@link Optional} containing the found entity, or empty if not found
     */
    Optional<T> findById(Long id);

    /**
     * Retrieves all entities of type {@code T} from the data source.
     *
     * @return a list of all entities
     */
    List<T> findAll();
}