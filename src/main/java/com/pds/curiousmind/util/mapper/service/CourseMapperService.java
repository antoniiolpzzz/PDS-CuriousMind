package com.pds.curiousmind.util.mapper.service;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.implementation.CourseMapperJSON;
import com.pds.curiousmind.util.mapper.implementation.CourseMapperYAML;

public enum CourseMapperService {
    INSTANCE;

    private final CourseMapperJSON jsonMapper = new CourseMapperJSON();
    private final CourseMapperYAML yamlMapper = new CourseMapperYAML();

    public Course toEntity(String data, CourseFormat format) {
        return switch (format) {
            case JSON -> jsonMapper.toEntity(data);
            case YAML -> yamlMapper.toEntity(data);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }

    public String fromEntity(Course course, CourseFormat format) {
        return switch (format) {
            case JSON -> jsonMapper.fromEntity(course);
            case YAML -> yamlMapper.fromEntity(course);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}

