package com.pds.curiousmind.model.library.implementation;

import com.pds.curiousmind.model.library.Library;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import com.pds.curiousmind.persistence.provider.AdapterProvider;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Singleton library for managing {@link Course} objects in memory and synchronizing with persistent storage.
 * <p>
 * This class provides thread-safe operations for adding, updating, removing, and retrieving courses. It delegates
 * persistence operations to an {@link ICourseAdapter} and maintains an in-memory list of courses for fast access.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Thread-safe in-memory storage using {@link java.util.concurrent.CopyOnWriteArrayList}.</li>
 *   <li>Delegates persistence to an adapter provided by {@link AdapterProvider}.</li>
 *   <li>Supports reloading from persistent storage.</li>
 *   <li>Provides methods to add, update, remove, and retrieve courses by ID or name.</li>
 *   <li>Implements the {@link com.pds.curiousmind.model.library.Library} interface.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     CourseLibrary library = CourseLibrary.INSTANCE;
 *     Course course = new Course(...);
 *     library.add(course);
 *     List&lt;Course&gt; allCourses = library.getAll();
 *     Course found = library.getById(1L);
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
public enum CourseLibrary implements Library<Course> {
    INSTANCE;

    /** Adapter for persistence operations on courses. */
    private final ICourseAdapter courseAdapter;
    /** Thread-safe in-memory list of courses. */
    private final List<Course> courses;

    /**
     * Initializes the CourseLibrary singleton, loading all courses from persistent storage.
     */
    CourseLibrary() {
        this.courseAdapter = AdapterProvider.INSTANCE().getCourseAdapter();
        this.courses = new CopyOnWriteArrayList<>(courseAdapter.findAll());
    }

    /**
     * Reloads the in-memory list of courses from persistent storage.
     * This clears the current list and repopulates it with the latest data.
     */
    public void reload() {
        courses.clear();
        courses.addAll(courseAdapter.findAll());
    }

    /**
     * Adds a new course to the library and persistent storage.
     *
     * @param course the course to add
     * @return the saved course (may be the same or a persisted version)
     */
    @Override
    public Course add(Course course) {
        Course saved = courseAdapter.save(course);
        courses.add(saved);
        return saved;
    }

    /**
     * Updates an existing course in the library and persistent storage.
     *
     * @param course the course to update
     * @return the updated course, or null if update failed
     */
    @Override
    public Course update(Course course) {
        Course updated = courseAdapter.update(course);
        if (updated != null) {
            courses.replaceAll(c -> c.getId().equals(updated.getId()) ? updated : c);
        }
        return updated;
    }

    /**
     * Removes a course from the library and persistent storage.
     *
     * @param course the course to remove
     * @return {@code true} if the course was removed, {@code false} otherwise
     */
    @Override
    public boolean remove(Course course) {
        boolean deleted = courseAdapter.delete(course);
        if (deleted) {
            courses.remove(course);
        }
        return deleted;
    }

    /**
     * Returns an unmodifiable list of all courses in the library.
     *
     * @return a list of all courses
     */
    @Override
    public List<Course> getAll() {
        return Collections.unmodifiableList(courses);
    }

    /**
     * Retrieves a course by its unique identifier.
     *
     * @param id the unique identifier of the course
     * @return the course with the specified id, or {@code null} if not found
     */
    @Override
    public Course getById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves a course by its name.
     *
     * @param name the name of the course
     * @return the course with the specified name, or {@code null} if not found
     */
    public Course getByName(String name) {
        return courses.stream()
                .filter(course -> course.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
