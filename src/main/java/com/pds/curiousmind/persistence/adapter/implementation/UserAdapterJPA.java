package com.pds.curiousmind.persistence.adapter.implementation;

import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter;
import com.pds.curiousmind.util.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

/**
 * JPA adapter implementation for user persistence operations.
 * <p>
 * This enum singleton implements the {@link IUserAdapter} interface, providing CRUD operations
 * and custom queries for {@link User} entities using JPA. It manages the persistence context
 * through an {@link EntityManagerFactory} and handles transactions for each operation.
 * </p>
 *
 * <p>
 * Usage example:
 * <pre>
 *     UserAdapterJPA.INSTANCE.setEntityManagerFactory(factory);
 *     User user = new User(...);
 *     User saved = UserAdapterJPA.INSTANCE.save(user);
 *     Optional<User> found = UserAdapterJPA.INSTANCE.findById(1L);
 * </pre>
 * </p>
 *
 * <p>
 * Error handling is performed with transaction rollbacks and logging via {@link Logger}.
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.model.user.User
 * @see com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter
 */
public enum UserAdapterJPA implements IUserAdapter {
    /**
     * Singleton instance of the user JPA adapter.
     */
    INSTANCE;

    /**
     * The factory for creating {@link EntityManager} instances.
     */
    private EntityManagerFactory entityManagerFactory;

    /**
     * Sets the {@link EntityManagerFactory} to be used by this adapter.
     *
     * @param entityManagerFactory the factory to set
     */
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Persists a new user entity in the database.
     *
     * @param user the user to save
     * @return the saved user, or null if the operation failed
     */
    @Override
    public User save(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            Logger.error("Failed to save user: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Updates an existing user entity in the database.
     *
     * @param user the user to update
     * @return the updated user, or null if the operation failed
     */
    @Override
    public User update(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User merged = entityManager.merge(user);
            transaction.commit();
            return merged;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            Logger.error("Failed to update user: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Deletes a user entity from the database.
     *
     * @param user the user to delete
     * @return true if the user was deleted successfully, false otherwise
     */
    @Override
    public boolean delete(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User managedUser = entityManager.merge(user);
            entityManager.remove(managedUser);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            Logger.error("Failed to delete user: " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Finds a user by their unique identifier.
     *
     * @param id the user ID
     * @return an {@link Optional} containing the found user, or empty if not found
     */
    @Override
    public Optional<User> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(User.class, id));
        } finally {
            entityManager.close();
        }
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users
     */
    @Override
    public List<User> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Finds a user by their unique username.
     *
     * @param username the username to search for
     * @return an {@link Optional} containing the found user, or empty if not found
     */
    @Override
    public Optional<User> findByUsername(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
        } finally {
            entityManager.close();
        }
    }
}
