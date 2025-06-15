package com.pds.curiousmind.util.mapper.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.interfaces.ICourseMapper;

public class CourseMapperYAML implements ICourseMapper<String> {
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @Override
    public Course toEntity(String data) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("Input YAML data must not be null or blank");
        }
        try {
            return objectMapper.readValue(data, Course.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse YAML to Course", e);
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
            throw new RuntimeException("Failed to convert Course to YAML", e);
        }
    }
}

