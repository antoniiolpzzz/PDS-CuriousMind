package com.pds.curiousmind.persistence.adapter.implementation;

import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.EntityGraph;

import java.util.List;
import java.util.Map;

public enum UserAdapterJPA implements IUserAdapter {
    INSTANCE;

    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save user", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update user", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User managedUser = entityManager.merge(user);
            entityManager.remove(managedUser);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete user", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityGraph<?> graph = entityManager.getEntityGraph("User.fullGraph");
            return entityManager.find(User.class, id, Map.of("jakarta.persistence.fetchgraph", graph));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityGraph<?> graph = entityManager.getEntityGraph("User.fullGraph");
            return entityManager.createQuery("SELECT u FROM User u", User.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User findByUsername(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityGraph<?> graph = entityManager.getEntityGraph("User.fullGraph");
            List<User> results = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            entityManager.close();
        }
    }
}
