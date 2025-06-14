package com.pds.curiousmind.persistence.adapter.implementation;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.EntityGraph;

import java.util.List;
import java.util.Map;

public enum CourseAdapterJPA implements ICourseAdapter {
    INSTANCE;

    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save entity", e);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void update(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update entity", e);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void delete(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course managedCourse = entityManager.merge(course);
            entityManager.remove(managedCourse);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete entity", e);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public Course findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityGraph<?> graph = entityManager.getEntityGraph("Course.fullGraph");
            return entityManager.find(Course.class, id, Map.of("jakarta.persistence.fetchgraph", graph));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Course> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityGraph<?> graph = entityManager.getEntityGraph("Course.fullGraph");
            return entityManager.createQuery("SELECT c FROM Course c", Course.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Course> findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityGraph<?> graph = entityManager.getEntityGraph("Course.fullGraph");
            return entityManager.createQuery("SELECT c FROM Course c WHERE c.name = :name", Course.class)
                .setParameter("name", name)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }
}
