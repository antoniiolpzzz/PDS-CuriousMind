package com.pds.curiousmind.util.mapper.interfaces;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.IMapper;

/**
 * Mapper interface for converting between {@link Course} entities and data representations.
 * <p>
 * This interface extends the generic {@link IMapper} interface, specializing it for the {@link Course}
 * entity. It defines methods for mapping from a data object (such as a DTO, file, or other format)
 * to a {@link Course} entity and vice versa.
 * </p>
 *
 * <p>
 * Implementations of this interface are responsible for handling the conversion logic for specific
 * data formats (e.g., JSON, YAML, XML) or data transfer objects related to courses.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     ICourseMapper&lt;CourseDTO&gt; mapper = new CourseMapperJSON();
 *     Course course = mapper.toEntity(courseDTO);
 *     CourseDTO dto = mapper.fromEntity(course);
 * </pre>
 * </p>
 *
 * @param <R> the data representation type (e.g., DTO, file, or format-specific object)
 * @author Antonio López Toboso
 * @see com.pds.curiousmind.model.course.Course
 * @see com.pds.curiousmind.util.mapper.IMapper
 */
public interface ICourseMapper<R> extends IMapper<Course, R> {
    /**
     * Converts data to a Course entity.
     *
     * @param data the data to convert
     * @return the Course entity
     */
    @Override
    Course toEntity(R data);

    /**
     * Converts a Course entity to data.
     *
     * @param entity the Course entity to convert
     * @return the converted data
     */
    @Override
    R fromEntity(Course entity);
}
