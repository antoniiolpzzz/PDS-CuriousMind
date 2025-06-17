package com.pds.curiousmind.persistence.adapter.implementation;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public enum CourseAdapterJPA implements ICourseAdapter {
    INSTANCE;

    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Course save(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(course);
            transaction.commit();
            return course;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save course", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Course update(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course merged = entityManager.merge(course);
            transaction.commit();
            return merged;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update course", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean delete(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course managedCourse = entityManager.merge(course);
            entityManager.remove(managedCourse);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Course.class, id));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Course> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Removed entity graph to avoid MultipleBagFetchException
            return entityManager.createQuery("SELECT c FROM Course c", Course.class)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Course> findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT c FROM Course c WHERE c.name = :name", Course.class)
                .setParameter("name", name)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }
}
