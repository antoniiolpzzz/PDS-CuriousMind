package com.pds.curiousmind.persistence.adapter.implementation;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import com.pds.curiousmind.util.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

/**
 * JPA adapter implementation for course persistence operations.
 * <p>
 * This enum singleton implements the {@link ICourseAdapter} interface, providing CRUD operations
 * and custom queries for {@link Course} entities using JPA. It manages the persistence context
 * through an {@link EntityManagerFactory} and handles transactions for each operation.
 * </p>
 *
 * <p>
 * Usage example:
 * <pre>
 *     CourseAdapterJPA.INSTANCE.setEntityManagerFactory(factory);
 *     Course course = new Course(...);
 *     Course saved = CourseAdapterJPA.INSTANCE.save(course);
 *     Optional&lt;Course&gt; found = CourseAdapterJPA.INSTANCE.findById(1L);
 * </pre>
 * </p>
 *
 * <p>
 * Error handling is performed with transaction rollbacks and logging via {@link Logger}.
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.model.course.Course
 * @see com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter
 */
public enum CourseAdapterJPA implements ICourseAdapter {
    /**
     * Singleton instance of the course JPA adapter.
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
     * Persists a new course entity in the database.
     *
     * @param course the course to save
     * @return the saved course, or null if the operation failed
     */
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
            Logger.error("Failed to save course: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Updates an existing course entity in the database.
     *
     * @param course the course to update
     * @return the updated course, or null if the operation failed
     */
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
            Logger.error("Failed to update course: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Deletes a course entity from the database.
     *
     * @param course the course to delete
     * @return true if the course was deleted successfully, false otherwise
     */
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
            Logger.error("Failed to delete course: " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Finds a course by its unique identifier.
     *
     * @param id the course ID
     * @return an {@link Optional} containing the found course, or empty if not found
     */
    @Override
    public Optional<Course> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Course.class, id));
        } finally {
            entityManager.close();
        }
    }

    /**
     * Retrieves all courses from the database.
     *
     * @return a list of all courses
     */
    @Override
    public List<Course> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT c FROM Course c", Course.class)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Finds courses by their name.
     *
     * @param name the name of the course(s) to search for
     * @return a list of courses matching the given name
     */
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
