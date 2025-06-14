package com.pds.curiousmind.persistence.adapter.implementation;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

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
        //TODO: Implement findById method
        return null;
    }

    @Override
    public List<Course> findAll() {
        //TODO: Implement findAll method
        return List.of();
    }

    @Override
    public List<Course> findByName(String name) {
        //TODO: Implement findByName method
        return List.of();
    }
}
