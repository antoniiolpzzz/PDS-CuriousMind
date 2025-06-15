package com.pds.curiousmind.util.mapper.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.interfaces.ICourseMapper;

public class CourseMapperJSON implements ICourseMapper<String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Course toEntity(String data) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("Input JSON data must not be null or blank");
        }
        try {
            return objectMapper.readValue(data, Course.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON to Course", e);
        }
    }

    @Override
    public String fromEntity(Course entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Course entity must not be null");
        }
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert Course to JSON", e);
        }
    }

}
