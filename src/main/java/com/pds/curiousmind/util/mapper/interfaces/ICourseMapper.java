package com.pds.curiousmind.util.mapper.interfaces;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.IMapper;

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
