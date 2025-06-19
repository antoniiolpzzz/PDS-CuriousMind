package com.pds.curiousmind.model.library.implementation;

import com.pds.curiousmind.model.library.Library;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter;
import com.pds.curiousmind.persistence.provider.AdapterProvider;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Singleton library for managing {@link User} objects in memory and synchronizing with persistent storage.
 * <p>
 * This class provides thread-safe operations for adding, updating, removing, and retrieving users. It delegates
 * persistence operations to an {@link IUserAdapter} and maintains an in-memory list of users for fast access.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Thread-safe in-memory storage using {@link java.util.concurrent.CopyOnWriteArrayList}.</li>
 *   <li>Delegates persistence to an adapter provided by {@link AdapterProvider}.</li>
 *   <li>Supports reloading from persistent storage.</li>
 *   <li>Provides methods to add, update, remove, and retrieve users by ID or username.</li>
 *   <li>Implements the {@link com.pds.curiousmind.model.library.Library} interface.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     UserLibrary library = UserLibrary.INSTANCE;
 *     User user = new User(...);
 *     library.add(user);
 *     List&lt;User&gt; allUsers = library.getAll();
 *     User found = library.getById(1L);
 *     User byUsername = library.getByUsername("alice");
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
public enum UserLibrary implements Library<User> {
    INSTANCE;

    /** Adapter for persistence operations on users. */
    private final IUserAdapter userAdapter;
    /** Thread-safe in-memory list of users. */
    private final List<User> users;

    /**
     * Initializes the UserLibrary singleton, loading all users from persistent storage.
     */
    UserLibrary() {
        this.userAdapter = AdapterProvider.INSTANCE().getUserAdapter();
        this.users = new CopyOnWriteArrayList<>(userAdapter.findAll());
    }

    /**
     * Reloads the in-memory list of users from persistent storage.
     * This clears the current list and repopulates it with the latest data.
     */
    public void reload() {
        users.clear();
        users.addAll(userAdapter.findAll());
    }

    /**
     * Adds a new user to the library and persistent storage.
     *
     * @param user the user to add
     * @return the saved user (may be the same or a persisted version)
     */
    @Override
    public User add(User user) {
        User saved = userAdapter.save(user);
        users.add(saved);
        return saved;
    }

    /**
     * Updates an existing user in the library and persistent storage.
     *
     * @param user the user to update
     * @return the updated user, or null if update failed
     */
    @Override
    public User update(User user) {
        User updated = userAdapter.update(user);
        if (updated != null) {
            users.replaceAll(u -> u.getId().equals(updated.getId()) ? updated : u);
        }
        return updated;
    }

    /**
     * Removes a user from the library and persistent storage.
     *
     * @param user the user to remove
     * @return {@code true} if the user was removed, {@code false} otherwise
     */
    @Override
    public boolean remove(User user) {
        boolean deleted = userAdapter.delete(user);
        if (deleted) {
            users.remove(user);
        }
        return deleted;
    }

    /**
     * Returns an unmodifiable list of all users in the library.
     *
     * @return a list of all users
     */
    @Override
    public List<User> getAll() {
        return Collections.unmodifiableList(users);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user
     * @return the user with the specified id, or {@code null} if not found
     */
    @Override
    public User getById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user
     * @return the user with the specified username, or {@code null} if not found
     */
    public User getByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
