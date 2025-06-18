package com.pds.curiousmind.persistence.adapter.interfaces;

import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.persistence.adapter.IAdapter;

import java.util.Optional;

/**
 * Adapter interface for user-specific persistence operations.
 * <p>
 * Extends the generic {@link IAdapter} interface to provide additional methods
 * for retrieving {@link User} entities from the data source. This interface is intended
 * to be implemented by classes that handle user-related CRUD operations and custom queries.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     IUserAdapter userAdapter = ...;
 *     Optional<User> user = userAdapter.findByUsername("johndoe");
 * </pre>
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.model.user.User
 * @see com.pds.curiousmind.persistence.adapter.IAdapter
 */
public interface IUserAdapter extends IAdapter<User> {

    /**
     * Finds a user by their unique username.
     *
     * @param username the username to search for
     * @return an {@link Optional} containing the found user, or empty if not found
     */
    Optional<User> findByUsername(String username);
}
