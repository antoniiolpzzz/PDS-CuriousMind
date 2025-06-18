package com.pds.curiousmind.persistence.adapter.interfaces;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.persistence.adapter.IAdapter;

import java.util.List;

/**
 * Adapter interface for course-specific persistence operations.
 * <p>
 * Extends the generic {@link IAdapter} interface to provide additional methods
 * for retrieving {@link Course} entities from the data source. This interface is intended
 * to be implemented by classes that handle course-related CRUD operations and custom queries.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     ICourseAdapter courseAdapter = ...;
 *     List<Course> courses = courseAdapter.findByName("Programming");
 * </pre>
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.model.course.Course
 * @see com.pds.curiousmind.persistence.adapter.IAdapter
 */
public interface ICourseAdapter extends IAdapter<Course> {

    /**
     * Finds courses by their name.
     *
     * @param name the name of the course(s) to search for
     * @return a list of courses matching the given name
     */
    List<Course> findByName(String name);
}
