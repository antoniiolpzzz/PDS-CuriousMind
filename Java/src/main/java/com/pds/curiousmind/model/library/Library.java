package com.pds.curiousmind.model.library;

import java.util.List;

/**
 * Interface for a generic library that manages objects of a specific type.
 * <p>
 * This interface defines the contract for a library that can add, remove, update, and retrieve objects of type {@code T}.
 * It is intended to be implemented by classes that manage collections of domain objects, such as users, courses, or other entities.
 *
 * <h2>Key Responsibilities:</h2>
 * <ul>
 *   <li>Add new objects to the library.</li>
 *   <li>Remove existing objects from the library.</li>
 *   <li>Update objects in the library.</li>
 *   <li>Retrieve all objects managed by the library.</li>
 *   <li>Retrieve a specific object by its unique identifier.</li>
 * </ul>
 *
 * <h2>Type Parameters:</h2>
 * <ul>
 *   <li><b>T</b> - the type of objects managed by the library</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     Library&lt;User&gt; userLibrary = new UserLibrary();
 *     User user = new User("Alice");
 *     userLibrary.add(user);
 *     List&lt;User&gt; allUsers = userLibrary.getAll();
 *     User found = userLibrary.getById(1L);
 * </pre>
 *
 * @param <T> the type of objects managed by the library
 * @author Antonio
 * @since 1.0
 */
public interface Library<T> {

    /**
     * Adds a new object to the library.
     *
     * @param object the object to add
     * @return the added object (may be the same or a persisted version)
     */
    T add(T object);

    /**
     * Removes an object from the library.
     *
     * @param object the object to remove
     * @return {@code true} if the object was removed, {@code false} otherwise
     */
    boolean remove(T object);

    /**
     * Updates an existing object in the library.
     *
     * @param object the object to update
     * @return the updated object
     */
    T update(T object);

    /**
     * Retrieves all objects managed by the library.
     *
     * @return a list of all objects in the library
     */
    List<T> getAll();

    /**
     * Retrieves an object by its unique identifier.
     *
     * @param id the unique identifier of the object
     * @return the object with the specified id, or {@code null} if not found
     */
    T getById(Long id);

}
